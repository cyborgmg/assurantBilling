
package br.com.delphos.billing.braspag.conciliador.cliente;

import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for GetExportedFileRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetExportedFileRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{https://reconciliation.braspag.com.br}AbstractRequest">
 *       &lt;sequence>
 *         &lt;element name="MerchantId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="RequestingPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReferenceDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="FileFormatType" type="{http://www.w3.org/2001/XMLSchema}unsignedByte"/>
 *         &lt;element name="FileType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetExportedFileRequest", propOrder = {
    "merchantId",
    "requestingPassword",
    "referenceDate",
    "fileFormatType",
    "fileType"
})
public class GetExportedFileRequest
    extends AbstractRequest
{

    @XmlElement(name = "MerchantId")
    protected int merchantId;
    @XmlElement(name = "RequestingPassword")
    protected String requestingPassword;
    @XmlElement(name = "ReferenceDate", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Calendar referenceDate;
    @XmlElement(name = "FileFormatType")
    @XmlSchemaType(name = "unsignedByte")
    protected short fileFormatType;
    @XmlElement(name = "FileType")
    protected int fileType;

    /**
     * Gets the value of the merchantId property.
     * 
     */
    public int getMerchantId() {
        return merchantId;
    }

    /**
     * Sets the value of the merchantId property.
     * 
     */
    public void setMerchantId(int value) {
        this.merchantId = value;
    }

    /**
     * Gets the value of the requestingPassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestingPassword() {
        return requestingPassword;
    }

    /**
     * Sets the value of the requestingPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestingPassword(String value) {
        this.requestingPassword = value;
    }

    /**
     * Gets the value of the referenceDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Calendar getReferenceDate() {
        return referenceDate;
    }

    /**
     * Sets the value of the referenceDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceDate(Calendar value) {
        this.referenceDate = value;
    }

    /**
     * Gets the value of the fileFormatType property.
     * 
     */
    public short getFileFormatType() {
        return fileFormatType;
    }

    /**
     * Sets the value of the fileFormatType property.
     * 
     */
    public void setFileFormatType(short value) {
        this.fileFormatType = value;
    }

    /**
     * Gets the value of the fileType property.
     * 
     */
    public int getFileType() {
        return fileType;
    }

    /**
     * Sets the value of the fileType property.
     * 
     */
    public void setFileType(int value) {
        this.fileType = value;
    }

}
