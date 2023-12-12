package sap.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileUtils {
	private static final String FILE_EXTENSION = ".csv";

	private static final String DATA_COUNT = "_COUNT_";

	private static String FILE_DIRECTORY = "D:\\Java\\taxinvoicesData\\";

	private static File folder;

	private static File csvfile;

	/**
	 * @return
	 * @throws IOException 
	 * @throws Exception
	 */
	public static File createFolder() throws IOException {
		// 7일이전의 파일 및 폴더 삭제
		deleteDateFolder(FILE_DIRECTORY, 7);
		folder = new File(FILE_DIRECTORY + DateTimeUtils.getCrementDateStr());
		// 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
		if (!folder.exists()) {
			folder.mkdir(); // 폴더 생성합니다.
			log.info(folder.getPath() + " 폴더가 생성되었습니다.");
		} else {
			log.info(folder.getPath() + " 이미 폴더가 생성되어 있습니다.");
		}
		return folder;
	}

	/**
	 * @param <E>
	 * @param list
	 * @param structure
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public static <E> File createCSVFile(int dataCnt, String structure) throws IOException {
		String filepath = DateTimeUtils.getCrementDateTimeStr() + structure + DATA_COUNT + dataCnt + FILE_EXTENSION;
		csvfile = new File(folder, filepath);

		if (!csvfile.exists()) { // 파일이 존재하지 않으면 생성
			if (csvfile.createNewFile()) {
				log.info(csvfile.getPath() + " 파일 생성 성공");
			} else {
				log.info(csvfile.getPath() + " 파일 생성 실패");
			}

		} else { // 파일이 존재한다면
			log.info("파일이 이미 존재합니다.");
		}
		return csvfile;
	}

	/**
	 * 특정 날짜 이전의 폴더 삭제
	 * 
	 * @param rootDir
	 * @param gap     설정할 일수
	 * @return
	 * @throws IOException
	 */
	public static void deleteDateFolder(String dir, int gap) throws IOException {
		// Calendar 객체 생성
		Calendar cal = Calendar.getInstance();
		long todayMil = cal.getTimeInMillis(); // 현재 시간(밀리 세컨드)
		long oneDayMil = 24 * 60 * 60 * 1000; // 일 단위

		Calendar fileCal = Calendar.getInstance();
		Date fileDate = null;

		File path = new File(dir);
		File[] list = path.listFiles(); // 파일 리스트 가져오기

		for (int j = 0; j < list.length; j++) {
			// 파일의 마지막 수정시간 가져오기
			fileDate = new Date(list[j].lastModified());

			// 현재시간과 파일 수정시간 시간차 계산(단위 : 밀리 세컨드)
			fileCal.setTime(fileDate);
			long diffMil = todayMil - fileCal.getTimeInMillis();

			// 날짜로 계산
			int diffDay = (int) (diffMil / oneDayMil);

			// 3일이 지난 파일 삭제
			if (diffDay > gap && list[j].exists()) {
				Path rootDir = Paths.get(list[j].getAbsolutePath());
				Files.walk(rootDir).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
			}
		}
	}

}
