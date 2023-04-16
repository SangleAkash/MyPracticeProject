package com.hi.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hi.dao.ProductDao;
import com.hi.entity.Category;
import com.hi.entity.Product;
import com.hi.entity.Supplier;
import com.hi.service.ProductService;
import com.hi.validation.ValidatedObject;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao pd;

	@Override
	public boolean saveProduct(Product product) {
		String productId = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(LocalDateTime.now());
		product.setProductId(productId);
		boolean isSave = pd.saveProduct(product);
		return isSave;
	}

	@Override
	public Product getProductById(String productId) {
		Product productById = pd.getProductById(productId);
		return productById;
	}

	@Override
	public List<Product> getAllProduct() {
		List<Product> allProduct = pd.getAllProduct();
		return allProduct;
	}

	@Override
	public boolean deleteProductById(String productId) {
		boolean isDeleted = pd.deleteProductById(productId);
		return isDeleted;
	}

	@Override
	public boolean updateProduct(Product product) {
		boolean updateProduct = pd.updateProduct(product);
		return updateProduct;
	}

	@Override
	public List<Product> sortProductById_ASC() {
		List<Product> list = pd.sortProductById_ASC();
		return list;
	}

	@Override
	public List<Product> sortProductByName_DESC() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getMaxPriceProduct() {
		List<Product> list = pd.getMaxPriceProduct();
		return list;
	}

	@Override
	public double getMaxPrice() {
		double maxPrice = pd.getMaxPrice();
		return maxPrice;
	}

	@Override
	public double countSumOfProductPrice() {
		double countSumOfProductPrice = pd.countSumOfProductPrice();
		return countSumOfProductPrice;
	}

	@Override
	public int getTotalCountOfProducts() {
		int totalCountOfProducts = pd.getTotalCountOfProducts();
		return totalCountOfProducts;
	}

	public List<Product> readExcelSheet(String path) {
		Product product = null;
		List<Product> list = new ArrayList<>();
		try {
			FileInputStream fis = new FileInputStream(path);
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.rowIterator();
			while (rows.hasNext()) {

				Row row = rows.next();
				int rowNum = row.getRowNum();
				if (rowNum == 0) {
					continue;
				}
				product = new Product();
				String productId = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(LocalDateTime.now());
				product.setProductId(productId);
				Iterator<Cell> cells = row.cellIterator();
				while (cells.hasNext()) {
					Cell cell = cells.next();
					int columnIndex = cell.getColumnIndex();
					switch (columnIndex) {
					case 0: {
						product.setProductName(cell.getStringCellValue());
						break;
					}
					case 1: {
						Supplier supplier = new Supplier();
						supplier.setSupplierId((int) cell.getNumericCellValue());

						break;
					}
					case 2: {
						Category category = new Category();
						category.setCategoryId((int) cell.getNumericCellValue());

						break;
					}
					case 3: {
						product.setProductQty((int) cell.getNumericCellValue());
						break;
					}
					case 4: {
						product.setProductPrice(cell.getNumericCellValue());
						;
						break;
					}
					}

				}
				list.add(product);
//					CellType cellType = cell.getCellType();
//					if (cellType == CellType.STRING) {
//						System.out.print(cell.getStringCellValue()+"\t");
//					} else {
//						System.out.print(cell.getNumericCellValue()+"\t");
//					}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	@Override
	public String uploadSheet(MultipartFile myfile) {
		String path = "src/main/resources";
		File file = new File(path);
		String absolutePath = file.getAbsolutePath();
		System.out.println(absolutePath);
		String msg = null;
		try {
			byte[] data = myfile.getBytes();
			FileOutputStream fos = new FileOutputStream(
					new File(absolutePath + File.separator + myfile.getOriginalFilename()));
			fos.write(data);
			List<Product> list = readExcelSheet(absolutePath + File.separator + myfile.getOriginalFilename());

			msg = pd.uploadProduct(list);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return msg;
	}

	String excludedRows = "";
	int totalRecordCount = 0;
	Map<String, Object> map = new HashMap<String, Object>();
	Map<String, String> validatedError = new HashMap<String, String>();
	Map<Integer, Map<String, String>> errorMap = new HashMap<Integer, Map<String, String>>();

	public List<Product> readExcel(String path) {
		Product product = null;
		List<Product> list = new ArrayList<>();
		try {
			FileInputStream fis = new FileInputStream(path);
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheetAt(0);
			totalRecordCount = sheet.getLastRowNum();
			Iterator<Row> rowIterator = sheet.rowIterator();
			int rowNo = 0;
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (rowNo == 0) {
					continue;
				}
				product = new Product();
				String productId = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(LocalDateTime.now());
				product.setProductId(productId);
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					int columnIndex = cell.getColumnIndex();
					switch (columnIndex) {
					case 0: {
						product.setProductName(cell.getStringCellValue());
						break;
					}
					case 1: {
						Supplier supplier = new Supplier();
						supplier.setSupplierId((int) cell.getNumericCellValue());
						break;
					}
					case 2: {
						Category category = new Category();
						category.setCategoryId((int) cell.getNumericCellValue());
						break;
					}
					case 3: {
						product.setProductQty((int) cell.getNumericCellValue());
						break;
					}
					case 4: {
						product.setProductPrice(cell.getNumericCellValue());
						break;
					}
					}
				}
				validatedError = ValidatedObject.validateProduct(product);
				if (validatedError == null || validatedError.isEmpty()) {
					list.add(product);
				} else {
					int rowno = row.getRowNum() + 1;
					errorMap.put(rowno, validatedError);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	@Override
	public Map<String, Object> uploadSheet(CommonsMultipartFile file, HttpSession session) {
		String path = session.getServletContext().getRealPath("/t");
		String originalFilename = file.getOriginalFilename();
		int[] arr;
		byte[] data = file.getBytes();
		try {
			FileOutputStream fos = new FileOutputStream(path + File.separator + originalFilename);
			fos.write(data);
			List<Product> readExcel = readExcel(path + File.separator + originalFilename);
			arr = pd.uploadProductList(readExcel);

			map.put("Total Record In Sheet", totalRecordCount);
			map.put("Uploaded Records In DB", arr[0]);
			map.put("Exists Records In DB", arr[1]);
			map.put("Total Excluded ", errorMap.size());
			map.put("Bad Record Row Number", errorMap);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public String exportToExcel(HttpSession session) {
		String[] columns = { "productName", "categoryId", "supplierId", "productQty", "productPrice" };
		 List<Product> list=getAllProduct();
		 String filePath=null;
		try {
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("product");
			Font headerFond = workbook.createFont();
			headerFond.setBold(true);
			headerFond.setFontHeightInPoints((short) 14);
			headerFond.setColor(IndexedColors.RED.getIndex());
			
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFond);
			Row row = sheet.createRow(0);
			for (int i = 0; i < columns.length; i++) {
				Cell cell = row.createCell(i);
				cell.setCellValue(columns[i]);
				cell.setCellStyle(headerCellStyle);

			}
			int rowNo=1;
			for (Product product : list) {
				Row createRow = sheet.createRow(rowNo++);
				createRow.createCell(0).setCellValue(product.getProductId());
			    createRow.createCell(1).setBlank();
				createRow.createCell(2).setBlank();
				createRow.createCell(3).setCellValue(product.getProductQty());
				createRow.createCell(4).setCellValue(product.getProductPrice());
			}
			for (int i = 0; i < columns.length; i++) {
				sheet.autoSizeColumn(i);
			}
			filePath=System.getProperty("user.home");
			filePath=filePath+"/Downloads";
			FileOutputStream fileOut = new FileOutputStream(filePath + File.separator + "product.xlsx");
			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			
		}
		
		return filePath+File.separator+"product.xlsx";
	}

}
