package sap.config;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static sap.type.ErrorCode.*;
import com.sap.conn.jco.ConversionException;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterField;
import com.sap.conn.jco.JCoParameterFieldIterator;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoRecordField;
import com.sap.conn.jco.JCoRecordFieldIterator;
import com.sap.conn.jco.JCoRecordMetaData;
import com.sap.conn.jco.JCoTable;
import com.sap.conn.jco.ext.DestinationDataProvider;
import com.sap.conn.jco.ext.ServerDataProvider;
import com.sap.conn.jco.server.DefaultServerHandlerFactory;
import com.sap.conn.jco.server.JCoServer;
import com.sap.conn.jco.server.JCoServerFactory;
import com.sap.conn.jco.server.JCoServerFunctionHandler;

import sap.controller.HTTaxinvoiceIssueController;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class JCoConnectionManager {

	static String SERVER_NAME1 = "SERVER";
	static String DESTINATION_NAME1 = "ABAP_AS_WITHOUT_POOL";

	@Value("${sap.connect.host}")
	private String ashost;
	@Value("${sap.connect.sysnr}")
	private String sysnr;
	@Value("${sap.connect.client}")
	private String client;
	@Value("${sap.connect.user}")
	private String user;
	@Value("${sap.connect.passwd}")
	private String passwd;
	@Value("${sap.connect.lang}")
	private String lang;

	@Value("${jco.server.connection_count}")
	private String conneCount;
	@Value("${jco.server.progid}")
	private String progid;
	@Value("${jco.server.repository_destination}")
	private String repoDestination;
	@Value("${jco.server.gwserv}")
	private String gwserv;
	@Value("${jco.server.gwhost}")
	private String gwhost;

	@PostConstruct
	public void init() {
		Properties connectProperties = new Properties();
		connectProperties.setProperty(DestinationDataProvider.JCO_ASHOST, ashost); // SAP 호스트 정보
		connectProperties.setProperty(DestinationDataProvider.JCO_SYSNR, sysnr); // 인스턴스번호
		connectProperties.setProperty(DestinationDataProvider.JCO_CLIENT, client); // SAP 클라이언트
		connectProperties.setProperty(DestinationDataProvider.JCO_USER, user); // SAP유저명
		connectProperties.setProperty(DestinationDataProvider.JCO_PASSWD, passwd); // SAP 패스워드
		connectProperties.setProperty(DestinationDataProvider.JCO_LANG, lang); // 언어
		createDataFile(DESTINATION_NAME1, "jcoDestination", connectProperties);

		Properties serverProperties = new Properties();
		serverProperties.setProperty(ServerDataProvider.JCO_GWHOST, gwhost);
		serverProperties.setProperty(ServerDataProvider.JCO_GWSERV, gwserv);
		serverProperties.setProperty(ServerDataProvider.JCO_PROGID, progid);
		serverProperties.setProperty(ServerDataProvider.JCO_REP_DEST, repoDestination);
		serverProperties.setProperty(ServerDataProvider.JCO_CONNECTION_COUNT, conneCount);
		createDataFile(SERVER_NAME1, "jcoServer", serverProperties);
	}

	/**
	 * 프로퍼티 파일 생성
	 * 
	 * @param name
	 * @param suffix
	 * @param properties
	 */
	static void createDataFile(String name, String suffix, Properties properties) {
		File cfg = new File(name + "." + suffix);
		if (!cfg.exists()) {
			try {
				FileOutputStream fos = new FileOutputStream(cfg, false);
				properties.store(fos, "for tests only !");
				fos.close();
			} catch (Exception e) {
				throw new RuntimeException(NOT_CREATE_FILE_ERROR + cfg.getName(), e);
			}
		}
	}

	public JCoDestination getConnectionSAP() throws JCoException {
		return JCoDestinationManager.getDestination(DESTINATION_NAME1);
	}

	/**
	 * RFC TCP/IP 연결
	 * 
	 * @param funName
	 */
	public void stepRfcServer(String funName) {
		log.info("RFC 접속 서버 기동");
		JCoServer server;
		try {
			server = JCoServerFactory.getServer(SERVER_NAME1);
		} catch (JCoException ex) {
			throw new RuntimeException(
					NOT_CREATE_SAP_SERVER_ERROR + SERVER_NAME1 + ", because of " + ex.getMessage(), ex);
		}
		JCoServerFunctionHandler stfcConnectionHandler = hTTaxinvoiceIssueController();
		DefaultServerHandlerFactory.FunctionHandlerFactory factory = new DefaultServerHandlerFactory.FunctionHandlerFactory();
		factory.registerHandler(funName, stfcConnectionHandler);
		server.setCallHandlerFactory(factory);
		server.start();
		log.info("The program can be stoped using <ctrl>+<c>");
	}

	/**
	 * import 파라미터 세팅
	 * 
	 * @param inputMapList 
	 * @param inputTabName 입력 테이블명
	 * @param jCoFunction  연결 jCoFunction
	 * @throws ConversionException
	 */
	public void setRFCImport(List<Map<String, Object>> inputMapList, String inputTabName, JCoFunction jCoFunction)
			throws ConversionException {
		JCoParameterList jCoParameterList = jCoFunction.getTableParameterList();
		JCoParameterFieldIterator jCoParameterFieldIterator = jCoParameterList.getParameterFieldIterator();

		JCoParameterField jCoParameterField;
		while (jCoParameterFieldIterator.hasNextField()) {
			jCoParameterField = jCoParameterFieldIterator.nextParameterField();
			if (jCoParameterField.isTable() && jCoParameterField.getName().equals(inputTabName)) {
				JCoTable jCoTable = jCoParameterField.getTable();
				JCoRecordFieldIterator fieldIter = jCoTable.getRecordFieldIterator();
				JCoRecordField field = null;

				for (Map<String, Object> inputMap : inputMapList) {
					jCoTable.appendRow();
					while (fieldIter.hasNextField()) {
						field = fieldIter.nextRecordField();
						field.setValue(inputMap.get(field.getName()));
					}
					fieldIter.reset();
				}

			}
		}
	}

	/**
	 * export 파라미터 세팅
	 * 
	 * @param outputTabName 출력 테이이블명
	 * @param jCoFunction 연결 jCoFunction
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> setRFCExport(String outputTabName, JCoFunction jCoFunction) {
		List<Map<String, Object>> outMapList = new ArrayList<>();
		JCoTable jCoTable = jCoFunction.getTableParameterList().getTable(outputTabName);

		// jCoTable 데이타 loop
		JCoRecordMetaData metaData = jCoFunction.getTableParameterList().getTable(outputTabName).getRecordMetaData();
		for (int i = 0; i < jCoTable.getNumRows(); i++) {
			jCoTable.setRow(i);
			Map<String, Object> outMap = new LinkedHashMap<String, Object>();
			for (int j = 0; j < metaData.getFieldCount(); j++) {
				if (metaData.getType(j) == 2) {
					outMap.put(metaData.getName(j), jCoTable.getFloat(metaData.getName(j)));
				} else {
					outMap.put(metaData.getName(j), jCoTable.getString(metaData.getName(j)));
				}
			}
			outMapList.add(outMap);
		}
		return outMapList;
	}

	@Bean
	public HTTaxinvoiceIssueController hTTaxinvoiceIssueController() {
		return new HTTaxinvoiceIssueController();
	}

}