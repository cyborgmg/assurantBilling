
package br.com.delphos.billing.braspag.conciliador;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CardType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CardType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Undefined"/>
 *     &lt;enumeration value="Visa"/>
 *     &lt;enumeration value="Mastercard"/>
 *     &lt;enumeration value="Elo"/>
 *     &lt;enumeration value="Diners"/>
 *     &lt;enumeration value="Cabal"/>
 *     &lt;enumeration value="Hipercard"/>
 *     &lt;enumeration value="Agiplan"/>
 *     &lt;enumeration value="Banescard"/>
 *     &lt;enumeration value="CredSystem"/>
 *     &lt;enumeration value="Esplanada"/>
 *     &lt;enumeration value="CredZ"/>
 *     &lt;enumeration value="SoroCred"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CardType")
@XmlEnum
public enum CardType {

    @XmlEnumValue("Undefined")
    UNDEFINED("Undefined"),
    @XmlEnumValue("Visa")
    VISA("Visa"),
    @XmlEnumValue("Mastercard")
    MASTERCARD("Mastercard"),
    @XmlEnumValue("Elo")
    ELO("Elo"),
    @XmlEnumValue("Diners")
    DINERS("Diners"),
    @XmlEnumValue("Cabal")
    CABAL("Cabal"),
    @XmlEnumValue("Hipercard")
    HIPERCARD("Hipercard"),
    @XmlEnumValue("Agiplan")
    AGIPLAN("Agiplan"),
    @XmlEnumValue("Banescard")
    BANESCARD("Banescard"),
    @XmlEnumValue("CredSystem")
    CRED_SYSTEM("CredSystem"),
    @XmlEnumValue("Esplanada")
    ESPLANADA("Esplanada"),
    @XmlEnumValue("CredZ")
    CRED_Z("CredZ"),
    @XmlEnumValue("SoroCred")
    SORO_CRED("SoroCred"),
    
    // TODO Verificar com a Braspag o porquê de não ter Credit, 
    // ou de usar bandeiras ao invés do tipo de cartão (e.g. crédito, débito)
    @XmlEnumValue("Credit")
    CREDIT("Credit");
    
    private final String value;

    CardType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CardType fromValue(String v) {
        for (CardType c: CardType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
