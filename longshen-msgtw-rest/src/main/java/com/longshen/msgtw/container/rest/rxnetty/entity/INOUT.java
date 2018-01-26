package com.longshen.msgtw.container.rest.rxnetty.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class INOUT<IN,OUT> {
 
	IN input;
	OUT output;
}
