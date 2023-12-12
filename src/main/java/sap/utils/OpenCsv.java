package sap.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.opencsv.CSVWriter;
import com.opencsv.bean.BeanField;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import sap.type.StatusEnum;

public class OpenCsv {
	public static <T> String writeCsvData(List<T> beans, Class<T> type, File csvfile)
			throws Exception, FileNotFoundException, FileNotFoundException {
		// header strategy
		CustomMappingStrategy<T> columnStrategy = new CustomMappingStrategy<T>();
		columnStrategy.setType(type);
		FileOutputStream fos = null;

		fos = new FileOutputStream(csvfile);

		try (Writer writer = new OutputStreamWriter(fos, Charset.forName("EUC-KR"));) {
			StatefulBeanToCsv<T> csvWriter = new StatefulBeanToCsvBuilder<T>(writer)
					.withSeparator(CSVWriter.DEFAULT_SEPARATOR).withQuotechar(CSVWriter.DEFAULT_QUOTE_CHARACTER)
					.withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER).withLineEnd(CSVWriter.DEFAULT_LINE_END)
					.withMappingStrategy(columnStrategy).build();

			csvWriter.write(beans);
			
		} catch (IOException e) {
			e.printStackTrace();
			return StatusEnum.BAD_REQUEST.code;
		}
		fos.close();
		return StatusEnum.OK.code;
	}

	public static class CustomMappingStrategy<T> extends ColumnPositionMappingStrategy<T> {
		@Override
		public String[] generateHeader(T bean) throws CsvRequiredFieldEmptyException {
			final int numColumns = getFieldMap().values().size();
			super.generateHeader(bean);

			String[] customHeader = new String[numColumns];

			BeanField<T, Integer> beanField;
			for (int i = 0; i < numColumns; i++) {
				beanField = findField(i);
				String columnHeaderName = extractHeaderName(beanField);
				customHeader[i] = columnHeaderName;

			}
			return customHeader;
		}

		public String extractHeaderName(BeanField<T, Integer> beanField) {
			if (beanField == null || beanField.getField() == null
					|| beanField.getField().getDeclaredAnnotationsByType(CsvBindByName.class).length == 0) {
				return StringUtils.EMPTY;
			}

			final CsvBindByName bindByNameAnnotation = beanField.getField()
					.getDeclaredAnnotationsByType(CsvBindByName.class)[0];
			return bindByNameAnnotation.column();
		}
	}

}
