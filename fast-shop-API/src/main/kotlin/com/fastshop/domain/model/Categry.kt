package com.fastshop.domain.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Category(@Id @GeneratedValue(strategy=GenerationType.IDENTITY)val id:Int=0,var name:String?="",)