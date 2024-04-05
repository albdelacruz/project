package com.mkyong;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MetadataCategoriesDto {
	
private String agreementNumber = StringUtils.EMPTY;
	
	private String channel = StringUtils.EMPTY;
	
	private String customerName = StringUtils.EMPTY;
	
	private String deliveryDate = StringUtils.EMPTY;
	
	private String eoe = StringUtils.EMPTY;
	
	private String fileType = StringUtils.EMPTY;
	
	private String generatedBy = StringUtils.EMPTY;
	
	private String locale = StringUtils.EMPTY;
	
	private String notificationPeriod = StringUtils.EMPTY;
	
	private String pos = StringUtils.EMPTY;
	
	private String salesDistrict = StringUtils.EMPTY;
	
	private String salesOrg = StringUtils.EMPTY;
	
	private String soldTo = StringUtils.EMPTY;
	
	private String status = StringUtils.EMPTY;
	
	private String tier = StringUtils.EMPTY;
	
	private String currency = StringUtils.EMPTY;
	
	

	public MetadataCategoriesDto(String agreementNumber, String channel, String customerName, String deliveryDate,
			String eoe, String fileType, String generatedBy, String locale, String notificationPeriod, String pos,
			String salesDistrict, String salesOrg, String soldTo, String status, String tier, String currency) {
		super();
		this.agreementNumber = agreementNumber;
		this.channel = channel;
		this.customerName = customerName;
		this.deliveryDate = deliveryDate;
		this.eoe = eoe;
		this.fileType = fileType;
		this.generatedBy = generatedBy;
		this.locale = locale;
		this.notificationPeriod = notificationPeriod;
		this.pos = pos;
		this.salesDistrict = salesDistrict;
		this.salesOrg = salesOrg;
		this.soldTo = soldTo;
		this.status = status;
		this.tier = tier;
		this.currency = currency;
	}
	
	public MetadataCategoriesDto() {}

	public String getAgreementNumber() {
		return agreementNumber;
	}

	public void setAgreementNumber(String agreementNumber) {
		this.agreementNumber = agreementNumber;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getEoe() {
		return eoe;
	}

	public void setEoe(String eoe) {
		this.eoe = eoe;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getGeneratedBy() {
		return generatedBy;
	}

	public void setGeneratedBy(String generatedBy) {
		this.generatedBy = generatedBy;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getNotificationPeriod() {
		return notificationPeriod;
	}

	public void setNotificationPeriod(String notificationPeriod) {
		this.notificationPeriod = notificationPeriod;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public String getSalesDistrict() {
		return salesDistrict;
	}

	public void setSalesDistrict(String salesDistrict) {
		this.salesDistrict = salesDistrict;
	}

	public String getSalesOrg() {
		return salesOrg;
	}

	public void setSalesOrg(String salesOrg) {
		this.salesOrg = salesOrg;
	}

	public String getSoldTo() {
		return soldTo;
	}

	public void setSoldTo(String soldTo) {
		this.soldTo = soldTo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTier() {
		return tier;
	}

	public void setTier(String tier) {
		this.tier = tier;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "MetadataCategoriesDto [agreementNumber=" + agreementNumber + ", channel=" + channel + ", customerName="
				+ customerName + ", deliveryDate=" + deliveryDate + ", eoe=" + eoe + ", fileType=" + fileType
				+ ", generatedBy=" + generatedBy + ", locale=" + locale + ", notificationPeriod=" + notificationPeriod
				+ ", pos=" + pos + ", salesDistrict=" + salesDistrict + ", salesOrg=" + salesOrg + ", soldTo=" + soldTo
				+ ", status=" + status + ", tier=" + tier + ", currency=" + currency + ", getAgreementNumber()="
				+ getAgreementNumber() + ", getChannel()=" + getChannel() + ", getCustomerName()=" + getCustomerName()
				+ ", getDeliveryDate()=" + getDeliveryDate() + ", getEoe()=" + getEoe() + ", getFileType()="
				+ getFileType() + ", getGeneratedBy()=" + getGeneratedBy() + ", getLocale()=" + getLocale()
				+ ", getNotificationPeriod()=" + getNotificationPeriod() + ", getPos()=" + getPos()
				+ ", getSalesDistrict()=" + getSalesDistrict() + ", getSalesOrg()=" + getSalesOrg() + ", getSoldTo()="
				+ getSoldTo() + ", getStatus()=" + getStatus() + ", getTier()=" + getTier() + ", getCurrency()="
				+ getCurrency() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	

}
