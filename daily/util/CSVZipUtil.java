//package util;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.sql.CallableStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipOutputStream;
//import javax.servlet.http.HttpServletResponse;
//import oracle.jdbc.OracleTypes;
//import au.com.bytecode.opencsv.CSVWriter;
//
//public class CSVZipUtil {
//
//	private String zipSm = "超过6万条数据后，以CSV格式导出。";//导出zip文件说明
//	/*
//	 * add by li_dyue
//	 * date: 20120324
//	 * function:compose csv files and export zip
//	 *
//	 *  csvFileName 暂时生成的csv文件名称
//	 *  exportZipName 要导出的zip文件名称
//	 *  includeHeaders csv文件的列表的第一列，例如：纳税人识别号、纳税人名称、税务机关等
//	 *  dataPO 给csv文件赋值的PO们
//	 *  querysql 需要查询的sql语句
//	 *  exportMax 定义一次最多导出多少条csv文件
//	 *  resultNumber 一共需要导出多少条记录
//	 *
//	 *  文件给生成到本地，查出一条就往文件里写一条，
//	 *  如果要把这些数据都放到内存中再压缩，那么在大数据量的时候，一定会发生内存溢出问题。
//	 *
//	 */
//
//	public void exportZip(HttpServletResponse response, String csvFileName, String exportZipName,
//						  String[] includeHeaders, String[] dataPO, String querysql, int resultNumber)
//			throws DataAccessException {
//
//		JDBCDataSource jDBCDataSource = JDBCDataSource.getJDBCDataSource();
//		List rs_list = new ArrayList();
//		PageListData pageListData = new PageListData();
//		PreparedStatement preparedStatement = null;
//		ResultSet result = null;
//		Connection conn = null;
//		File tempFile = null;
//		CSVWriter writer = null;
//		ZipOutputStream zos = null;
//		FileInputStream fis = null;
//		//path: 先在服务器上生成csv文件的路径
//		StringBuffer path = new StringBuffer();
//		path.append("c:/lee/");
//		String fileExtension = ".csv";
//		String c = "";
//		int countAll = 0;
//		int bs = -1;//倍数
//		int exportMax = 60000;
//		int temp = 1;
//		String executeStr = "";
//		StringBuffer bf = new StringBuffer();
//		int t1 = (int) System.currentTimeMillis();
//		String[] data = new String[dataPO.length];
//		try {
//			conn = jDBCDataSource.getConnection();
//
//			countAll = resultNumber;
//			if(countAll/exportMax>=1){
//				bs = countAll/exportMax;
//			}
//			while(bs>=0){
//				if(countAll>=(exportMax*temp)){
//					executeStr = "SELECT *  FROM (select rownum myNum,TempAA.* FROM (" + querysql + ")TempAA ) where myNum>="
//							+ (temp-1)*exportMax + " and myNum<=" + exportMax*temp;
//					preparedStatement = conn.prepareStatement(executeStr);
//					result = preparedStatement.executeQuery(executeStr);
//					tempFile = new File(path+csvFileName+temp+fileExtension);
//					System.out.println("csvFileName:"+csvFileName+temp+fileExtension);
//					writer = new CSVWriter(new FileWriter(tempFile));
//					writer.writeNext(includeHeaders);
//
//					while (result.next()) {
//						//String[] data = new String[dataPO.length];
//						//!!!  此处的for循环，严重影响了导出的效率，暂无好的解决方案
//                                                for(int i=0;i<dataPO.length;i++){
//							data[i] =  StringUtil.isoToGbk(result.getString(dataPO[i]));
//						}
//						writer.writeNext(data);
//					}
//					writer.close();
//					if (result != null) {
//						result.close();
//						result = null;
//					}
//					if (preparedStatement != null) {
//						preparedStatement.close();
//						preparedStatement = null;
//					}
//				}else if(countAll%exportMax!=0){
//					executeStr = "SELECT *  FROM (select rownum myNum,aa.* FROM (" + querysql + ")aa ) where myNum>="
//							+ (temp-1)*exportMax + " and myNum<=" + countAll;
//					preparedStatement = conn.prepareStatement(executeStr);
//					result = preparedStatement.executeQuery(executeStr);
//					tempFile = new File(path+csvFileName+temp+fileExtension);
//					System.out.println("csvFileName:"+csvFileName+temp+fileExtension);
//					writer = new CSVWriter(new FileWriter(tempFile));
//					writer.writeNext(includeHeaders);
//					while (result.next()) {
//
//						for(int i=0;i<dataPO.length;i++){
//							data[i] =  StringUtil.isoToGbk(result.getString(dataPO[i]));
//						}
//						writer.writeNext(data);
//					}
//					writer.close();
//					if (result != null) {
//						result.close();
//						result = null;
//					}
//					if (preparedStatement != null) {
//						preparedStatement.close();
//						preparedStatement = null;
//					}
//				}
//				bs-=1;
//				temp+=1;
//			}
//			int t2 = (int) System.currentTimeMillis();
//			System.out.println((t2-t1)/3600);
//
//			File file = new File("c:/lee/c.zip");
//			String outputFile = csvFileName+temp + ".csv";
//
//			response.setContentType("application/zip");
//			response.reset();
//			exportZipName = "aa";
//			response.setHeader("Content-Disposition",
//					"attachment; filename=\"" + "c.zip" + "\"" );
//			response.setCharacterEncoding("UTF-8");
//			int leng=0;
//			zos = new ZipOutputStream(response.getOutputStream());
//
//			for(int i = temp-1;i>0;i--){
//				//System.out.println("=:"+path+csvFileName+i+fileExtension);
//				fis = new FileInputStream(path+csvFileName+i+fileExtension);
//				ZipEntry z1 = new ZipEntry(csvFileName+temp+fileExtension);
//				zos.putNextEntry(z1);
//				byte[] b = new byte[1024];
//				while((leng = fis.read(b))!=-1){
//					zos.write(b,0,leng);
//				}
//			}
//			zos.close();
//			fis.close();
//
//		} catch (Exception e) {
//			throw new DataAccessException(e);
//		} finally {
//			// 释放资源
//			try {
//				if(response.getOutputStream() != null)
//					response.getOutputStream().close();
//			} catch (IOException e2) {
//				e2.printStackTrace();
//			}
//			try {
//				if(zos!=null){
//					try {
//						zos.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//					zos = null;
//				}
//				if(fis!=null){
//					try {
//						fis.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//					fis = null;
//				}
//
//				if(writer!=null){
//					try {
//						writer.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//					writer = null;
//				}
//				if (result != null) {
//					result.close();
//					result = null;
//				}
//				if (preparedStatement != null) {
//					preparedStatement.close();
//					preparedStatement = null;
//				}
//
//			} catch (SQLException e1) {
//				throw new DataAccessException(e1);
//			}
//		}
//
//
//	}
//	public void deleteCsvFiles(String path,String fileName){
//
//	}
//
//	//如果导出内容小于6000条，那么导出excel格式；如果导出内容大于6000条，那么导出cvs格式，然后再压缩。
//	public boolean ExportToExcel(int resultCountAll){
//		boolean exportExcel = false;
//		if(resultCountAll<6000){
//			exportExcel = true;
//		}
//		return exportExcel;
//	}
//	public String getZipSm() {
//		return zipSm;
//	}
//	public void setZipSm(String zipSm) {
//		this.zipSm = zipSm;
//	}
//}
