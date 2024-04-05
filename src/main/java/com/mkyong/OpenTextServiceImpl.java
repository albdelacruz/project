package com.mkyong;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mmm.ms.utils.LogHelper;

import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


@Component
@Slf4j
public class OpenTextServiceImpl implements OpenTextService {
	
	
	private static final Logger LOG = LogManager.getLogger(OpenTextServiceImpl.class);
	
	//@Value("${ipfg.opentext.rootFolderId}")
    private String parentID = "1525264";
    
	//@Value("${ipfg.opentext.type}")
    private String type = "144";
    
	//@Value("${ipfg.opentext.url}")
    private String openTextURL = "https://optxcf-d1.mmm.com/OTCS/livelink/api/v1";
    
	//@Value("${ipfg.opentext.userID}")
    private String userName = "ipfr_service";
    
	//@Value("${ipfg.opentext.pwd}")
    private String password = "IPFR!nonprod24";
	
	//@Value("${ipfg.opentext.category.id}")
	private String categoryId = "1067499";
	
	//@Value("${ipfg.opentext.category.attribute.agreementnumber}")
	private String categoryAttributeAgreementNumber="18";
	
	//@Value("${ipfg.opentext.category.attribute.channel}")
	private String categoryAttributeChannel = "14";
	
	
	//@Value("${ipfg.opentext.category.attribute.customerName}")
	private String categoryAttributeCustomerName="3";
	
	//@Value("${ipfg.opentext.category.attribute.deliverydate}")
	private String categoryAttributeDeliveryDate="4";
	
	//@Value("${ipfg.opentext.category.attribute.eoe}")
	private String categoryAttributeEoe="17";
	
	//@Value("${ipfg.opentext.category.attribute.filetype}")
	private String categoryAttributefileType="2";
	
	//@Value("${ipfg.opentext.category.attribute.generatedby}")
	private String categoryAttributeGeneratedBy="10";
	
	//@Value("${ipfg.opentext.category.attribute.locale}")
	private String categoryAttributeLocale="21";
	
	//@Value("${ipfg.opentext.category.attribute.notificationperiod}")
	private String categoryAttributeNotificationPeriod="20";
	
	//@Value("${ipfg.opentext.category.attribute.pos}")
	private String categoryAttributePos="16";
	
	//@Value("${ipfg.opentext.category.attribute.salesdistrict}")
	private String categoryAttributeSalesDistrict="6";
	
	//@Value("${ipfg.opentext.category.attribute.salesorg}")
	private String categoryAttributeSalesOrg="7";
	
	//@Value("${ipfg.opentext.category.attribute.soldto}")
	private String categoryAttributeSoldTo="5";
	
	//@Value("${ipfg.opentext.category.attribute.status}")
	private String categoryAttributeStatus="12";
	
	//@Value("${ipfg.opentext.category.attribute.tier}")
	private String categoryAttributeTier="15";
	
	
	//@Value("${ipfg-ui.ipfgreport.path}")
	private String ipfguiFolderPath = "/home/wes/Documents/testfiles";
	
	private String authToken;

	@Override
	public String getToken() throws Exception {
		LOG.info("Getting Auth Token");
		
		String authToken = null;
		
		LOG.info("getToken() ==== Initializing HTTP client");
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		
		//Set the user name and password parameters as part of the body
		RequestBody body = RequestBody.create( mediaType,"username="+userName+"&password="+password);
		//Build the header and body
		Request request = new Request.Builder().url(openTextURL+"/auth").method("POST", body).addHeader("Content-Type", "application/x-www-form-urlencoded").build();
		LOG.info("getToken() ==== REQUEST ====" + request.toString());
		Response response = client.newCall(request).execute();
		
		String JSONStr =response.body().string();
		JSONObject obj = new JSONObject(JSONStr);
		
		if(obj.has("error")) {
			LOG.info("getToken() ==== authToken ====" + obj.getString("error"));
	    	authToken = "";
	    	throw new Exception(JSONStr);
	    }
		
		authToken= obj.getString("ticket");
		LOG.info("getToken() ==== authToken ====" + authToken);
		
		return authToken;
	}

	@Override
	public String applyMetadata(String nodeId, MetadataCategoriesDto categories) {
		try {
			LOG.info("Applying Metadata on category " + categoryId + "--node " + nodeId);
			LOG.info("Category DTO" + "--" + categories);
			OkHttpClient client = new OkHttpClient().newBuilder().build();
			String otcsticket = getToken();
			String dvDate = categories.getDeliveryDate().substring(0, 4) + "-"
					+ categories.getDeliveryDate().substring(4, 6) + "-" + categories.getDeliveryDate().substring(6);
			RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
					.addFormDataPart("category_id", categoryId)
					.addFormDataPart(categoryId + "_" + categoryAttributeSalesOrg, categories.getSalesOrg())
					.addFormDataPart(categoryId + "_" + categoryAttributeSalesDistrict, categories.getSalesDistrict())
					.addFormDataPart(categoryId + "_" + categoryAttributeCustomerName, categories.getCustomerName())
					.addFormDataPart(categoryId + "_" + categoryAttributefileType, categories.getFileType())
					.addFormDataPart(categoryId + "_" + categoryAttributeDeliveryDate, dvDate)
					.addFormDataPart(categoryId + "_" + categoryAttributeSoldTo, categories.getSoldTo())
					.addFormDataPart(categoryId + "_" + categoryAttributeGeneratedBy, categories.getGeneratedBy())
					.addFormDataPart(categoryId + "_" + categoryAttributeNotificationPeriod, categories.getNotificationPeriod())
					.addFormDataPart(categoryId + "_" + categoryAttributeLocale, categories.getLocale())
					.addFormDataPart(categoryId + "_" + categoryAttributeChannel, categories.getChannel())
					.addFormDataPart(categoryId + "_" + categoryAttributeTier, categories.getTier())
					.addFormDataPart(categoryId + "_" + categoryAttributePos, categories.getPos())
					.addFormDataPart(categoryId + "_" + categoryAttributeEoe, categories.getEoe())
					.addFormDataPart(categoryId + "_" + categoryAttributeAgreementNumber, categories.getAgreementNumber())
					.addFormDataPart(categoryId + "_" + categoryAttributeStatus, categories.getStatus()).build();
			String finalUrl =  openTextURL + "/nodes/" + nodeId +"/categories";
			LOG.info("Opentext metadata URL:" + finalUrl);
			LOG.info("RequestBody: {}", requestBody.toString());
			Request request = new Request.Builder().url(finalUrl)
					.method("POST", requestBody).addHeader("OTCSTICKET", otcsticket).build();
			LOG.info("Applying meta data..");
			Response response = client.newCall(request).execute();
			LOG.info("Response: {}", response.body().string());
			LOG.info("Applying meta data complete..");
			return response.body().string();
		} catch (Exception e) {
			LOG.info(e.getMessage());
			return "Exception: " + e.getMessage();
		}
	}

	@Override
	public long uploadFile(File file, String fileName) throws Exception {

		LOG.info("Upload File");
		LOG.info("Filename " + "--" + fileName);
		//applyPermissions(parentID, groupId);
		long nodeID;
		try {
		if (!file.exists()) {
			throw new Exception("invalid file: file does not exist::" + file.getAbsolutePath());
		}
		MediaType mediaType = MediaType.parse("application/xls");
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("type", type)
				.addFormDataPart("parent_id", parentID).addFormDataPart("name", fileName)
				.addFormDataPart("file",parentID,RequestBody.create(file, mediaType)).build();
		Request request = new Request.Builder().url(openTextURL + "/nodes").method("POST", requestBody)
				.addHeader("OTCSTICKET", getToken()).build();
		LOG.info("Uploading file to content server");
		
		Response response = client.newCall(request).execute();
		String JSONStr = response.body().string();
		
		System.out.println("*****************"+JSONStr);
		
		JSONObject obj = new JSONObject(JSONStr);
		if (obj.has("error")) {
			LOG.error("createDocument: " + JSONStr);
			throw new Exception("Unable to upload document");
		} else {
			nodeID = obj.getLong("id");
		}
		}catch(Exception ex) {
			ex.printStackTrace();
			throw new Exception(ex);
		}
		return nodeID;

	
	}

	@Override
public boolean sendReportsToOpenText() {
		List<File> files = getFiles(ipfguiFolderPath);
		if(CollectionUtils.isNotEmpty(files)) {
			List<File> xlsFiles = files.stream().filter(f -> f.getName().contains(".xls")).collect(Collectors.toList());
			xlsFiles.stream().forEach(xlsFile -> {
				try {
					long fileID = uploadFile(xlsFile, xlsFile.getName());
					if(fileID != 0) {
						Optional<File> metadata = files.stream().filter(file -> file.getName().equalsIgnoreCase(getFileName(xlsFile.getName()) + ".xml")).findAny();
						applyMetadata(String.valueOf(fileID), parseMetadata(metadata.get()));
						deleteReport(getFileName(xlsFile.getName()));
					}
				} catch (Exception e) {
					LOG.error("Error processing file: " + xlsFile.getName() + e.getMessage());
				}			
			});		
		}	
		return true;
	}
	
	public boolean deleteReport(String reportName) {
		String filePathExcel = ipfguiFolderPath +"/"+reportName+".xlsx";
		String filePathXML = ipfguiFolderPath +"/"+reportName+".xml";
		//delete excel and xml files for report name
		File excelFile = new File(filePathExcel);
		File xmlFile = new File(filePathXML);
		
		if (excelFile.delete() && xmlFile.delete())
		{
			LOG.info("====FILES FOR " + reportName + " HAS BEEN DELETED====");
			return true;
		}
		LOG.info("====FILES FOR " + reportName + " FAILED TO DELETE====");
		return false;
	}
	
	private String getFileName(String xlsFileName) {
		LOG.info("FILENAME:" + xlsFileName.substring(0, StringUtils.indexOf(xlsFileName, ".xls")));
		return xlsFileName.substring(0, StringUtils.indexOf(xlsFileName, ".xls"));
	}
	
	private List<File> getFiles(String ipfguiFolderPath){
	
		    return Stream.of(new File(ipfguiFolderPath).listFiles())
		      .filter(file -> !file.isDirectory()).collect(Collectors.toList());
	}
	
	private MetadataCategoriesDto parseMetadata(File xmlFile) throws Exception {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(xmlFile);
		document.getDocumentElement().normalize();

		NodeList nList = document.getElementsByTagName("attribute");
		MetadataCategoriesDto categories = new MetadataCategoriesDto();
		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node node = nList.item(temp);
			if (node.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) node;
				String attribute = eElement.getAttribute("name");
				LOG.info("Setting Attribute: " + attribute + " - value: " + node.getTextContent());
				switch (attribute) {
				case "File Type":
					categories.setFileType(node.getTextContent());
					break;
				case "Customer Name":
					categories.setCustomerName(node.getTextContent());
					break;
				case "Delivery Date":
					categories.setDeliveryDate(node.getTextContent());
					break;
				case "Sold To":
					categories.setSoldTo(node.getTextContent());
					break;
				case "Sales District":
					categories.setSalesDistrict(node.getTextContent());
					break;
				case "Sales Org":
					categories.setSalesOrg(node.getTextContent());
					break;
				case "Status":
					categories.setStatus(node.getTextContent());
					break;
				case "Notification Period":
					categories.setNotificationPeriod(node.getTextContent());
					break;
				case "Generated By":
					categories.setGeneratedBy(node.getTextContent());
					break;
				case "Locale":
					categories.setLocale(node.getTextContent());
					break;
				case "Currency":
					categories.setCurrency(node.getTextContent());
					break;
				case "Agreement Number":
					categories.setAgreementNumber(node.getTextContent());
					break;

				default:
					break;
				}
			}
		}
		LOG.info("Categories: " + categories.toString());
		return categories;
	}
	
	

}
