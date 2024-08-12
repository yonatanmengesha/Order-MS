package com.jotech.order.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection ="sequence")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sequence {
	@Id
	private String  id;
	private int sequence;

}
