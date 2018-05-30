package com.wangguang.web;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * EditorSupport - 日期
 *
 * @author xingkong1221
 * @date 2015-04-25
 */
public class DateEditor extends PropertyEditorSupport {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateFormat TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final DateFormat FORMAT_SECTION = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private DateFormat dateFormat;
    private boolean allowEmpty = true;

    public DateEditor() {
    }

    public DateEditor(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public DateEditor(DateFormat dateFormat, boolean allowEmpty) {
        this.dateFormat = dateFormat;
        this.allowEmpty = allowEmpty;
    }

    /**
     * 字符串转日期
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (this.allowEmpty && !StringUtils.hasText(text)) {
            // Treat empty String as null value.
            setValue(null);
        } else {
            try {
                if (this.dateFormat != null) {
                    setValue(this.dateFormat.parse(text));
                } else {
                    if (text.contains(":")) {
                    	if (text.split(":").length == 3) {
                    		setValue(TIME_FORMAT.parse(text));
                    	} else {
                    		setValue(FORMAT_SECTION.parse(text));
                    	}
                    } else {
                        setValue(DATE_FORMAT.parse(text));
                    }
                }
            } catch (ParseException ex) {
                throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
            }
        }
    }

    /**
     * 将日期转换成字符串
     */
    @Override
    public String getAsText() {
        Date value = (Date) getValue();
        DateFormat dateFormat = this.dateFormat;
        if (dateFormat == null) {
            dateFormat = TIME_FORMAT;
        }
        return value != null ? dateFormat.format(value) : "";
    }
}
