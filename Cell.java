import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = "type")
@JsonSubTypes({
  @Type(value = District.class, name = "District"),
  @Type(value = DetectiveToken.class, name = "DetectiveToken")
})

public abstract class Cell {

	Cell(){
		
	}

}


