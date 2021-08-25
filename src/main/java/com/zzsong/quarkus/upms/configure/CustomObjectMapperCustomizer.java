package com.zzsong.quarkus.upms.configure;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import io.quarkus.jackson.ObjectMapperCustomizer;

import javax.enterprise.context.ApplicationScoped;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 自定义消息转换器
 *
 * @author 宋志宗 on 2021/8/24
 */
@ApplicationScoped
public class CustomObjectMapperCustomizer implements ObjectMapperCustomizer {

  @Override
  public void customize(ObjectMapper objectMapper) {
    objectMapper
        // 对于空的对象转json的时候不抛出错误
        .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
        // 禁用遇到未知属性抛出异常
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        // 序列化BigDecimal时不使用科学计数法输出
        .configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true)
        .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));

    SimpleModule javaTimeModule = new JavaTimeModule();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
    javaTimeModule
        .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter))
        .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter))
        .addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter))
        .addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter))
        .addSerializer(LocalTime.class, new LocalTimeSerializer(timeFormatter))
        .addDeserializer(LocalTime.class, new LocalTimeDeserializer(timeFormatter));
    objectMapper.registerModule(javaTimeModule);

    // Long转String传输
    SimpleModule longToStrongModule = new SimpleModule();
    longToStrongModule.addSerializer(Long.class, ToStringSerializer.instance);
    longToStrongModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
    objectMapper.registerModule(longToStrongModule);
  }
}
