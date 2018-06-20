package com.wangguang.service;

import com.wangguang.entity.ExcelColumn;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BigDataExcelService {

    /**
     * 写入excel
     * @param values 写入的数据
     * @param colsName 列名
     * @param file 文件
     * @param fieldNames 每列对应的字段名
     * @throws IOException
     */
    public void writeToExcel(List<Map<String,String>> values, List<String> colsName, File file,List<String> fieldNames) throws IOException {
        if(!file.exists()){
            file.createNewFile();
        }
        FileOutputStream output = new FileOutputStream(file);  //读取的文件路径
        SXSSFWorkbook wb = new SXSSFWorkbook(1000);//内存中保留 10000 条数据，以免内存溢出，其余写入 硬盘
        SXSSFSheet sheet = wb.createSheet(String.valueOf("sheet"));
        wb.setSheetName(0, "sheet");
        sheet.trackAllColumnsForAutoSizing();
        sheet.autoSizeColumn(1);
        int rowNum = 0;
        if(colsName.size() > 0) {
            Row row = sheet.createRow(0);
            writeColumnName(colsName, row);
            rowNum++;
        }
        writeDataList(values,fieldNames,sheet,rowNum);
        wb.write(output);
        output.close();
    }

    /**
     * 将对象写入excel
     * @param objs
     * @param file
     * @throws IOException
     */
    public void writeToExcel(List objs, File file) throws IOException {
        if(!file.exists()){
            file.createNewFile();
        }
        FileOutputStream output = new FileOutputStream(file);  //读取的文件路径
        SXSSFWorkbook wb = new SXSSFWorkbook(1000);//内存中保留 10000 条数据，以免内存溢出，其余写入 硬盘
        SXSSFSheet sheet = wb.createSheet(String.valueOf("sheet"));
        wb.setSheetName(0, "sheet");
        sheet.trackAllColumnsForAutoSizing();
        sheet.autoSizeColumn(1);
        //列名
        List<String> colsName = new ArrayList<>();
        //字段名
        List<String> fieldNames = new ArrayList<>();
        if(objs != null && objs.size() > 0){
            for(Field filed : objs.get(0).getClass().getDeclaredFields()){
                ExcelColumn annotation = null;
                if((annotation = filed.getAnnotation(ExcelColumn.class)) != null){
                    colsName.add(annotation.colName());
                    fieldNames.add(filed.getName());
                }
            }
        }
        if(colsName.size() > 0) {
            Row row = sheet.createRow(0);
            writeColumnName(colsName, row);
        }
        try {
            writeDataList(objs,fieldNames,sheet);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        wb.write(output);
        output.close();
    }

    /**
     * 写入具体数据
     * @param values 数据map
     * @param sheet excel表单
     * @param fieldNames 列名
     */
    private void writeDataList(List<Object> values, List<String> fieldNames, Sheet sheet) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int rowNum = 1;
        for(Object obj : values){
            Row row = sheet.createRow(rowNum++);//创建行
            for(int i = 0; i < fieldNames.size();i++){
                Cell cell = row.createCell(i);
                cell.setCellType(CellType.STRING);
                Object invokeObj = obj.getClass().getMethod("get"+toUpperFirstChar(fieldNames.get(i))).invoke(obj);
                cell.setCellValue(invokeObj==null?"":invokeObj.toString());
            }
        }
    }

    /**
     * 写入具体数据
     * @param values 数据map
     * @param sheet excel表单
     * @param fieldNames 列名
     */
    private void writeDataList(List<Map<String,String>> values, List<String> fieldNames, Sheet sheet, int rowNum){
        for(int i = 0; i < values.size(); i++){
            Row row = sheet.createRow(i+rowNum);//创建行
            Map<String,String> value = values.get(i);
            for(int j = 0; j < fieldNames.size();j++){
                Cell cell = row.createCell(j);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(value.get(fieldNames.get(j)));
            }
        }
    }

    /**
     * 写入列名
     * @param cols 列名
     * @param row excel的一行
     */
    private void writeColumnName(List<String> cols,Row row){
        for(int i=0;i<cols.size();i++){
            Cell cell = row.createCell(i);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);//文本格式
            //sheet.setColumnWidth(i, fieldNames[i].length()*384); //设置单元格宽度
            cell.setCellValue(cols.get(i));//写入列名
        }
    }

    /**
     * 首字母大写
     * @param string
     * @return
     */
    public String toUpperFirstChar(String string) {
        char[] charArray = string.toCharArray();
        charArray[0] -= 32;
        return String.valueOf(charArray);
    }
}
