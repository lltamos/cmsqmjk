package com.quanmin.model.jpapo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by yang on 2017/7/24.
 */
@Entity
@Table(name = "pay_apliy_notify_info")
public class PayApliyNotifyInfo {
    private long id;
    private Date notifyTime;
    private String notifyType;
    private String notifyId;
    private String appId;
    private String charset;
    private String version;
    private String signType;
    private String sign;
    private String tradeNo;//支付宝交易号
    private String outTradeNo;//商户订单号
    private String outBizNo;
    private String buyerId;//买家在支付宝的用户id
    private String buyerLogonId;//买家支付宝账号
    private String sellerId;
    private String sellerEmail;
    private String tradeStatus;//实收金额
    private Double totalAmount;//交易金额
    private String receiptAmount;
    private String invoiceAmount;
    private String buyerPayAmount;
    private String pointAmount;
    private String refundFee;
    private String subject;
    private String body;
    private Date gmtCreate;
    private Date gmtPayment;//交易支付时间
    private Date gmtRefund;
    private Date gmtClose;
    private String fundBillList;
    private String passbackParams;
    private String voucherDetailList;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "notify_time", nullable = true)
    public Date getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
    }

    @Basic
    @Column(name = "notify_type", nullable = true, length = 64)
    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    @Basic
    @Column(name = "notify_id", nullable = true, length = 128)
    public String getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    @Basic
    @Column(name = "app_id", nullable = true, length = 32)
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Basic
    @Column(name = "charset", nullable = true, length = 10)
    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    @Basic
    @Column(name = "version", nullable = true, length = 3)
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Basic
    @Column(name = "sign_type", nullable = true, length = 10)
    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    @Basic
    @Column(name = "sign", nullable = true, length = 256)
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Basic
    @Column(name = "trade_no", nullable = true, length = 64)
    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    @Basic
    @Column(name = "out_trade_no", nullable = true, length = 64)
    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    @Basic
    @Column(name = "out_biz_no", nullable = true, length = 64)
    public String getOutBizNo() {
        return outBizNo;
    }

    public void setOutBizNo(String outBizNo) {
        this.outBizNo = outBizNo;
    }

    @Basic
    @Column(name = "buyer_id", nullable = true, length = 16)
    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    @Basic
    @Column(name = "buyer_logon_id", nullable = true, length = 100)
    public String getBuyerLogonId() {
        return buyerLogonId;
    }

    public void setBuyerLogonId(String buyerLogonId) {
        this.buyerLogonId = buyerLogonId;
    }

    @Basic
    @Column(name = "seller_id", nullable = true, length = 30)
    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    @Basic
    @Column(name = "seller_email", nullable = true, length = 100)
    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    @Basic
    @Column(name = "trade_status", nullable = true, length = 32)
    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    @Basic
    @Column(name = "total_amount", nullable = true, precision = 2)
    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Basic
    @Column(name = "receipt_amount", nullable = true, length = 12)
    public String getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(String receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    @Basic
    @Column(name = "invoice_amount", nullable = true, length = 12)
    public String getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(String invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    @Basic
    @Column(name = "buyer_pay_amount", nullable = true, length = 12)
    public String getBuyerPayAmount() {
        return buyerPayAmount;
    }

    public void setBuyerPayAmount(String buyerPayAmount) {
        this.buyerPayAmount = buyerPayAmount;
    }

    @Basic
    @Column(name = "point_amount", nullable = true, length = 12)
    public String getPointAmount() {
        return pointAmount;
    }

    public void setPointAmount(String pointAmount) {
        this.pointAmount = pointAmount;
    }

    @Basic
    @Column(name = "refund_fee", nullable = true, length = 12)
    public String getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(String refundFee) {
        this.refundFee = refundFee;
    }

    @Basic
    @Column(name = "subject", nullable = true, length = 256)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Basic
    @Column(name = "body", nullable = true, length = 400)
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Basic
    @Column(name = "gmt_create", nullable = true)
    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Basic
    @Column(name = "gmt_payment", nullable = true)
    public Date getGmtPayment() {
        return gmtPayment;
    }

    public void setGmtPayment(Date gmtPayment) {
        this.gmtPayment = gmtPayment;
    }

    @Basic
    @Column(name = "gmt_refund", nullable = true)
    public Date getGmtRefund() {
        return gmtRefund;
    }

    public void setGmtRefund(Date gmtRefund) {
        this.gmtRefund = gmtRefund;
    }

    @Basic
    @Column(name = "gmt_close", nullable = true)
    public Date getGmtClose() {
        return gmtClose;
    }

    public void setGmtClose(Date gmtClose) {
        this.gmtClose = gmtClose;
    }

    @Basic
    @Column(name = "fund_bill_list", nullable = true, length = 512)
    public String getFundBillList() {
        return fundBillList;
    }

    public void setFundBillList(String fundBillList) {
        this.fundBillList = fundBillList;
    }

    @Basic
    @Column(name = "passback_params", nullable = true, length = 512)
    public String getPassbackParams() {
        return passbackParams;
    }

    public void setPassbackParams(String passbackParams) {
        this.passbackParams = passbackParams;
    }

    @Basic
    @Column(name = "voucher_detail_list", nullable = true, length = 1024)
    public String getVoucherDetailList() {
        return voucherDetailList;
    }

    public void setVoucherDetailList(String voucherDetailList) {
        this.voucherDetailList = voucherDetailList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PayApliyNotifyInfo that = (PayApliyNotifyInfo) o;

        if (id != that.id) {
            return false;
        }
        if (notifyTime != null ? !notifyTime.equals(that.notifyTime) : that.notifyTime != null) {
            return false;
        }
        if (notifyType != null ? !notifyType.equals(that.notifyType) : that.notifyType != null) {
            return false;
        }
        if (notifyId != null ? !notifyId.equals(that.notifyId) : that.notifyId != null) {
            return false;
        }
        if (appId != null ? !appId.equals(that.appId) : that.appId != null) {
            return false;
        }
        if (charset != null ? !charset.equals(that.charset) : that.charset != null) {
            return false;
        }
        if (version != null ? !version.equals(that.version) : that.version != null) {
            return false;
        }
        if (signType != null ? !signType.equals(that.signType) : that.signType != null) {
            return false;
        }
        if (sign != null ? !sign.equals(that.sign) : that.sign != null) {
            return false;
        }
        if (tradeNo != null ? !tradeNo.equals(that.tradeNo) : that.tradeNo != null) {
            return false;
        }
        if (outTradeNo != null ? !outTradeNo.equals(that.outTradeNo) : that.outTradeNo != null) {
            return false;
        }
        if (outBizNo != null ? !outBizNo.equals(that.outBizNo) : that.outBizNo != null) {
            return false;
        }
        if (buyerId != null ? !buyerId.equals(that.buyerId) : that.buyerId != null) {
            return false;
        }
        if (buyerLogonId != null ? !buyerLogonId.equals(that.buyerLogonId) : that.buyerLogonId != null) {
            return false;
        }
        if (sellerId != null ? !sellerId.equals(that.sellerId) : that.sellerId != null) {
            return false;
        }
        if (sellerEmail != null ? !sellerEmail.equals(that.sellerEmail) : that.sellerEmail != null) {
            return false;
        }
        if (tradeStatus != null ? !tradeStatus.equals(that.tradeStatus) : that.tradeStatus != null) {
            return false;
        }
        if (totalAmount != null ? !totalAmount.equals(that.totalAmount) : that.totalAmount != null) {
            return false;
        }
        if (receiptAmount != null ? !receiptAmount.equals(that.receiptAmount) : that.receiptAmount != null) {
            return false;
        }
        if (invoiceAmount != null ? !invoiceAmount.equals(that.invoiceAmount) : that.invoiceAmount != null) {
            return false;
        }
        if (buyerPayAmount != null ? !buyerPayAmount.equals(that.buyerPayAmount) : that.buyerPayAmount != null) {
            return false;
        }
        if (pointAmount != null ? !pointAmount.equals(that.pointAmount) : that.pointAmount != null) {
            return false;
        }
        if (refundFee != null ? !refundFee.equals(that.refundFee) : that.refundFee != null) {
            return false;
        }
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) {
            return false;
        }
        if (body != null ? !body.equals(that.body) : that.body != null) {
            return false;
        }
        if (gmtCreate != null ? !gmtCreate.equals(that.gmtCreate) : that.gmtCreate != null) {
            return false;
        }
        if (gmtPayment != null ? !gmtPayment.equals(that.gmtPayment) : that.gmtPayment != null) {
            return false;
        }
        if (gmtRefund != null ? !gmtRefund.equals(that.gmtRefund) : that.gmtRefund != null) {
            return false;
        }
        if (gmtClose != null ? !gmtClose.equals(that.gmtClose) : that.gmtClose != null) {
            return false;
        }
        if (fundBillList != null ? !fundBillList.equals(that.fundBillList) : that.fundBillList != null) {
            return false;
        }
        if (passbackParams != null ? !passbackParams.equals(that.passbackParams) : that.passbackParams != null) {
            return false;
        }
        if (voucherDetailList != null ? !voucherDetailList.equals(that.voucherDetailList) : that.voucherDetailList != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (notifyTime != null ? notifyTime.hashCode() : 0);
        result = 31 * result + (notifyType != null ? notifyType.hashCode() : 0);
        result = 31 * result + (notifyId != null ? notifyId.hashCode() : 0);
        result = 31 * result + (appId != null ? appId.hashCode() : 0);
        result = 31 * result + (charset != null ? charset.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (signType != null ? signType.hashCode() : 0);
        result = 31 * result + (sign != null ? sign.hashCode() : 0);
        result = 31 * result + (tradeNo != null ? tradeNo.hashCode() : 0);
        result = 31 * result + (outTradeNo != null ? outTradeNo.hashCode() : 0);
        result = 31 * result + (outBizNo != null ? outBizNo.hashCode() : 0);
        result = 31 * result + (buyerId != null ? buyerId.hashCode() : 0);
        result = 31 * result + (buyerLogonId != null ? buyerLogonId.hashCode() : 0);
        result = 31 * result + (sellerId != null ? sellerId.hashCode() : 0);
        result = 31 * result + (sellerEmail != null ? sellerEmail.hashCode() : 0);
        result = 31 * result + (tradeStatus != null ? tradeStatus.hashCode() : 0);
        result = 31 * result + (totalAmount != null ? totalAmount.hashCode() : 0);
        result = 31 * result + (receiptAmount != null ? receiptAmount.hashCode() : 0);
        result = 31 * result + (invoiceAmount != null ? invoiceAmount.hashCode() : 0);
        result = 31 * result + (buyerPayAmount != null ? buyerPayAmount.hashCode() : 0);
        result = 31 * result + (pointAmount != null ? pointAmount.hashCode() : 0);
        result = 31 * result + (refundFee != null ? refundFee.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (gmtCreate != null ? gmtCreate.hashCode() : 0);
        result = 31 * result + (gmtPayment != null ? gmtPayment.hashCode() : 0);
        result = 31 * result + (gmtRefund != null ? gmtRefund.hashCode() : 0);
        result = 31 * result + (gmtClose != null ? gmtClose.hashCode() : 0);
        result = 31 * result + (fundBillList != null ? fundBillList.hashCode() : 0);
        result = 31 * result + (passbackParams != null ? passbackParams.hashCode() : 0);
        result = 31 * result + (voucherDetailList != null ? voucherDetailList.hashCode() : 0);
        return result;
    }
}
