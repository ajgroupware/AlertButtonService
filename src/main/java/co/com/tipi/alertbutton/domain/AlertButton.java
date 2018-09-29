package co.com.tipi.alertbutton.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * AlertButton
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-07-31T00:46:42.737Z")

public class AlertButton   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("phone")
  private String phone = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("address")
  private String address = null;

  @JsonProperty("contactName")
  private String contactName = null;

  @JsonProperty("contactPhone")
  private String contactPhone = null;

  @JsonProperty("lat")
  private Double lat = null;

  @JsonProperty("lng")
  private Double lng = null;

  /**
   * Estado del boton
   */
  public enum StatusEnum {
    ACTIVE("ACTIVE"),
    
    INACTIVE("INACTIVE");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("status")
  private StatusEnum status = null;

  public AlertButton id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Identificador del botón
   * @return id
  **/
  @ApiModelProperty(value = "Identificador del botón")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public AlertButton phone(String phone) {
    this.phone = phone;
    return this;
  }

  /**
   * Número de teléfono
   * @return phone
  **/
  @ApiModelProperty(value = "Número de teléfono")


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public AlertButton name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Nombre del lugar
   * @return name
  **/
  @ApiModelProperty(required = true, value = "Nombre del lugar")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public AlertButton address(String address) {
    this.address = address;
    return this;
  }

  /**
   * Dirección del lugar
   * @return address
  **/
  @ApiModelProperty(value = "Dirección del lugar")


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public AlertButton contactName(String contactName) {
    this.contactName = contactName;
    return this;
  }

  /**
   * Nombre del contacto
   * @return contactName
  **/
  @ApiModelProperty(value = "Nombre del contacto")


  public String getContactName() {
    return contactName;
  }

  public void setContactName(String contactName) {
    this.contactName = contactName;
  }

  public AlertButton contactPhone(String contactPhone) {
    this.contactPhone = contactPhone;
    return this;
  }

  /**
   * Teléfono del contacto
   * @return contactPhone
  **/
  @ApiModelProperty(value = "Teléfono del contacto")


  public String getContactPhone() {
    return contactPhone;
  }

  public void setContactPhone(String contactPhone) {
    this.contactPhone = contactPhone;
  }

  public AlertButton lat(Double lat) {
    this.lat = lat;
    return this;
  }

  /**
   * GPS Latitud
   * @return lat
  **/
  @ApiModelProperty(value = "GPS Latitud")


  public Double getLat() {
    return lat;
  }

  public void setLat(Double lat) {
    this.lat = lat;
  }

  public AlertButton lng(Double lng) {
    this.lng = lng;
    return this;
  }

  /**
   * GPS Longitud
   * @return lng
  **/
  @ApiModelProperty(value = "GPS Longitud")


  public Double getLng() {
    return lng;
  }

  public void setLng(Double lng) {
    this.lng = lng;
  }

  public AlertButton status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Estado del boton
   * @return status
  **/
  @ApiModelProperty(value = "Estado del boton")


  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AlertButton alertButton = (AlertButton) o;
    return Objects.equals(this.id, alertButton.id) &&
        Objects.equals(this.phone, alertButton.phone) &&
        Objects.equals(this.name, alertButton.name) &&
        Objects.equals(this.address, alertButton.address) &&
        Objects.equals(this.contactName, alertButton.contactName) &&
        Objects.equals(this.contactPhone, alertButton.contactPhone) &&
        Objects.equals(this.lat, alertButton.lat) &&
        Objects.equals(this.lng, alertButton.lng) &&
        Objects.equals(this.status, alertButton.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, phone, name, address, contactName, contactPhone, lat, lng, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AlertButton {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    contactName: ").append(toIndentedString(contactName)).append("\n");
    sb.append("    contactPhone: ").append(toIndentedString(contactPhone)).append("\n");
    sb.append("    lat: ").append(toIndentedString(lat)).append("\n");
    sb.append("    lng: ").append(toIndentedString(lng)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

