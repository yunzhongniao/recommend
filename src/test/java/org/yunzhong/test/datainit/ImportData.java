package org.yunzhong.test.datainit;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.catalina.core.ApplicationContext;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.yunzhong.service.model.HistoryData;
import org.yunzhong.test.BasicSpringTest;

import com.google.common.io.Files;

public class ImportData extends BasicSpringTest {
    private static final Logger log = LoggerFactory.getLogger(ImportData.class);

    @Autowired
    private ApplicationContext context;

    @Test
    public void testImportDataToDB() {
        File file = new File("E:/development/gukuozai/stockdata/stockdata");
        if (file.isDirectory()) {
            File[] children = file.listFiles();
            if (children != null) {
                for (File child : children) {
                    String id = child.getName().replace(".txt", "");
                    if (StringUtils.isEmpty(id)) {
                        log.warn("Failed to parse file name {1}", child.getName());
                        continue;
                    }
                    List<String> readLines;
                    try {
                        readLines = Files.readLines(child, Charset.forName("UTF-8"));
                        if (CollectionUtils.isEmpty(readLines)) {
                            log.info("There are no data in file {1}", child.getName());
                        } else {
                            List<HistoryData> datas = new ArrayList<>();
                            for (String readLine : readLines) {
                                StringTokenizer tokenizer = new StringTokenizer(readLine, ";");

                                HistoryData data = new HistoryData();
                                data.setId(id);
                                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                                String date = tokenizer.nextToken();
                                data.setDate(formatter.parse(date));

                                data.setOpen(Double.valueOf(tokenizer.nextToken()));
                                data.setMax(Double.valueOf(tokenizer.nextToken()));
                                data.setClose(Double.valueOf(tokenizer.nextToken()));
                                data.setDealCount(Long.valueOf(tokenizer.nextToken()));
                                data.setDealValue(Double.valueOf(tokenizer.nextToken()));
                                datas.add(data);
                            }
                        }
                    } catch (IOException e) {
                        log.error("Failed to read file {" + child.getName() + "}", e);
                    } catch (ParseException e) {
                        log.error("Failed to read file {" + child.getName() + "}", e);
                    }
                }
            }
        }
    }
}
