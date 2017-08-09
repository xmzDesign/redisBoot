/**
 * 
 */
package com.example.demo.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author xuminzhe 2017年8月9日 下午5:56:45
 * 
 *         自定义对象的序列化
 *
 */
public class RedisObjectSerializer implements RedisSerializer<Object> {
	// private Converter<Object, byte[]> serializer = new SerializingConverter();
	// private Converter<byte[], Object> deserializer = new
	// DeserializingConverter();
	static final byte[] EMPTY_ARRAY = new byte[0];

	@Override
	public Object deserialize(byte[] bytes) {
		if (isEmpty(bytes)) {
			return null;
		}
		ObjectInputStream oii = null;
		ByteArrayInputStream bis = null;
		bis = new ByteArrayInputStream(bytes);
		try {
			oii = new ObjectInputStream(bis);
			Object obj = oii.readObject();
			return obj;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public byte[] serialize(Object object) {
		if (object == null) {
			return EMPTY_ARRAY;
		}
		ObjectOutputStream obi = null;
		ByteArrayOutputStream bai = null;
		try {
			bai = new ByteArrayOutputStream();
			obi = new ObjectOutputStream(bai);
			obi.writeObject(object);
			byte[] byt = bai.toByteArray();
			return byt;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private boolean isEmpty(byte[] data) {
		return (data == null || data.length == 0);
	}
}
