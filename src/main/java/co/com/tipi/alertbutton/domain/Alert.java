package co.com.tipi.alertbutton.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Alert
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-07-31T00:46:42.737Z")

public class Alert   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("date")
  private String date = null;

  @JsonProperty("observation")
  private String observation = null;

  @JsonProperty("button")
  private AlertButton button = null;

  public Alert id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Identificador de la alerta
   * @return id
  **/
  @ApiModelProperty(value = "Identificador de la alerta")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Alert date(String date) {
    this.date = date;
    return this;
  }

  /**
   * Fecha de la alerta
   * @return date
  **/
  @ApiModelProperty(value = "Fecha de la alerta")


  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public Alert observation(String observation) {
    this.observation = observation;
    return this;
  }

  /**
   * Texto de observación
   * @return observation
  **/
  @ApiModelProperty(value = "Texto de observación")


  public String getObservation() {
    return observation;
  }

  public void setObservation(String observation) {
    this.observation = observation;
  }

  public Alert button(AlertButton button) {
    this.button = button;
    return this;
  }

  /**
   * Get button
   * @return button
  **/
  @ApiModelProperty(value = "")

  @Valid

  public AlertButton getButton() {
    return button;
  }

  public void setButton(AlertButton button) {
    this.button = button;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Alert alert = (Alert) o;
    return Objects.equals(this.id, alert.id) &&
        Objects.equals(this.date, alert.date) &&
        Objects.equals(this.observation, alert.observation) &&
        Objects.equals(this.button, alert.button);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, date, observation, button);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Alert {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    observation: ").append(toIndentedString(observation)).append("\n");
    sb.append("    button: ").append(toIndentedString(button)).append("\n");
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

