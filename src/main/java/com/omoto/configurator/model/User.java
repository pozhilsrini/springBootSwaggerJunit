package com.omoto.configurator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
//import io.swagger.annotations.ApiModelProperty;

/**
 * Created by omoto on 16/12/16.
 */
@Data
@Entity
@Table(name = "CUSTOMERSTABLE")
public class User {



    @Id
    @GeneratedValue
    @Column(name = "ID")
    @JsonProperty
    private int id;

    @NotNull
    @Size(min=2, max=40)
    @Column(name = "USERNAME")
    @JsonProperty(required = true)
    @ApiModelProperty(notes = "The name of the user", required = true)
    private String userName;

    @NotNull
    @Size(min=2, max=20)
    @Column(name = "PASSWORD")
    @JsonProperty(required = true)
    @ApiModelProperty(notes = "The password of the user", required = true)
    private String password;

}
