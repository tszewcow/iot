package org.iot.server.mapper;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.iot.server.document.User;
import org.iot.server.helper.EqualityChecker;
import org.iot.server.helper.TestDataGenerator;
import org.iot.server.to.UserTo;
import org.junit.Test;

public class UserMapperTest {

	private UserMapper userMapper = new UserMapper();

	@Test
	public void mapDocument2To_MappedValuesEqual()
	{
		User user = TestDataGenerator.getUser(1);
		
		UserTo mappedUser = userMapper.mapDocument2To(user);
		
		assertEquals(true, EqualityChecker.checkEquality(user, mappedUser));
	}

	@Test
	public void mapDocument2To_MappedValuesNotEqual()
	{
		User user = TestDataGenerator.getUser(1);
		
		UserTo mappedUser = userMapper.mapDocument2To(user);
		
		mappedUser.setId(mappedUser.getId().concat("1"));
		
		assertEquals(false, EqualityChecker.checkEquality(user, mappedUser));
	}
	
	@Test
	public void mapList2ToList_MappedValuesEqual()
	{
		List<User> userList = new ArrayList<User>();

		int listSize = 10;
		
		for(int x=0;x<listSize;x++)
			userList.add(TestDataGenerator.getUser(x));
		
		List<UserTo> mappedList = userMapper.mapDocuments2Tos(userList);
		
		for(int x=0;x<listSize;x++)
			assertEquals(true, EqualityChecker.checkEquality(userList.get(x), mappedList.get(x)));
	}

	@Test
	public void mapTo2Document_MappedValuesEqual()
	{
		UserTo userTo = TestDataGenerator.getUserTo(1);
		
		User demappedUser = userMapper.mapTo2Document(userTo);
		
		assertEquals(true, EqualityChecker.checkEquality(demappedUser, userTo));	
	}
}
