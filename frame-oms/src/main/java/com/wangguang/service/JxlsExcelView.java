package com.wangguang.service;

import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.LocalizedResourceHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.Map;

/**
 * 
 * 
 * @ClassName: JxlsExcelView
 * @Description: 导出Excel业务是实现类
 * @author: ranfi
 * @date: Jan 8, 2014 4:21:22 PM
 * 
 */
@Service
public class JxlsExcelView extends AbstractView {

	private static String content_type = "application/vnd.ms-excel; charset=UTF-8";
	private String extension = ".xlsx";

	private String fileName;
	private String location;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public JxlsExcelView() {
		setContentType(content_type);
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Workbook workbook;

		/* 获得XLS模板 */
		LocalizedResourceHelper helper = new LocalizedResourceHelper(getApplicationContext());
		Locale userLocale = RequestContextUtils.getLocale(request);
		Resource inputFile = helper.findLocalizedResource(location, extension, userLocale);

		/* 向POI的workbook中填充数据 */

		XLSTransformer transformer = new XLSTransformer();
		workbook = transformer.transformXLS(inputFile.getInputStream(), map);
//		if(map.containsKey("flag")){
//			Sheet sheet = workbook.getSheetAt(0);
//			List<Order> orderList = (List<Order>)map.get("result");
//			int j = 2;
//			int columnLength = sheet.getRow(2).getPhysicalNumberOfCells();
//			for(Order order : orderList){
//				for(int i=0;i<columnLength;i++){
//					if(i >= 5 && i < columnLength-12){
//						continue;
//					}else{
//						if(order.getOrderGoods().size() > 1){
//							sheet.addMergedRegion(new CellRangeAddress(j,j-1+order.getOrderGoods().size(), i,i));
//						}else{
//							break;
//						}
//					}
//
//				}
//				j += order.getOrderGoods().size();
//			}
//		}


		/* 设置HTML头实现重新定向输出流到客户端 */
		response.reset();
		response.setContentType(getContentType());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName,"UTF8") + "\"");

//		response.setCharacterEncoding("UTF-8");
		/* 将数据流写入 */
		ServletOutputStream out = response.getOutputStream();
		workbook.write(out);
		out.flush();
		out.close();
	}

}
