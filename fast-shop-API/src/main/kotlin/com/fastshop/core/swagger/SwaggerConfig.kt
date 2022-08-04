package com.fastshop.core.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.hateoas.client.LinkDiscoverers
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer
import org.springframework.plugin.core.SimplePluginRegistry
//@EnableSwagger2
@Configuration
class SwaggerConfig {

    //    @Primary
    @Bean // to solve ambiguos problem with hateoas
    fun discoverers() = LinkDiscoverers(
        SimplePluginRegistry
            .create(arrayListOf(CollectionJsonLinkDiscoverer()))
    )
}