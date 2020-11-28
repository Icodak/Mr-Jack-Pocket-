package board;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import board.detective.DetectiveToken;
import board.district.District;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = District.class, name = "District"),
		@Type(value = DetectiveToken.class, name = "DetectiveToken") })

public abstract class Cell {

	public Cell() {

	}

}
