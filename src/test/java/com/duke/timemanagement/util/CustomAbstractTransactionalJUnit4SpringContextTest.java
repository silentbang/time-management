package com.duke.timemanagement.util;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

// Ignore for JUnit
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:applicationContext-test.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class CustomAbstractTransactionalJUnit4SpringContextTest {

}
