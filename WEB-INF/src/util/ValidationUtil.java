package util;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.validator.Arg;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.Form;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.ValidatorException;
import org.apache.commons.validator.ValidatorResources;
import org.apache.commons.validator.ValidatorResult;
import org.apache.commons.validator.ValidatorResults;
import org.xml.sax.SAXException;

import servlet.LineConfirmServlet;

public class ValidationUtil {

	private static ResourceBundle apps =
	        ResourceBundle.getBundle(
	            "messageResources");
	public static List<String> validate(Object form) throws IOException {
		InputStream in = LineConfirmServlet.class.getResourceAsStream("/validation.xml");
		ValidatorResources resources = null;
		try {
			resources = new ValidatorResources(in);
		} catch (SAXException e) {
			throw new RuntimeException(e);
		} finally{
			in.close();
		}

		Validator validator = new Validator(resources, form.getClass().getCanonicalName());
		validator.setParameter(Validator.BEAN_PARAM, form);

		Form formObj = resources.getForm(Locale.getDefault(), form.getClass().getCanonicalName());

		List<String> errorMessages = new ArrayList<String>();
		try {

			ValidatorResults results = validator.validate();
//			Iterator<?> propertyNames = results.getPropertyNames().iterator();
			Iterator<?> propertyNames = formObj.getFields().iterator();
			while (propertyNames.hasNext()) {
				Field property = (Field) propertyNames.next();
				String propertyName = property.getProperty();;

				ValidatorResult result = results.getValidatorResult(propertyName);
				Iterator<?> actNames = result.getActions();
				while(actNames.hasNext()){
					String actName = (String)actNames.next();

					if(!result.isValid(actName)){
						ValidatorAction action = resources.getValidatorAction(actName);
						errorMessages.add(buildMessage(formObj, propertyName, action));
					}
				}
			}
		} catch (ValidatorException e) {
			throw new RuntimeException(e);
		}

		return errorMessages;
	}

	public static String buildMessage(Form form, String propertyName,
			ValidatorAction action) {
		String message = apps.getString(action.getMsg());
		String actName = action.getName();

		Field field = form.getField(propertyName);

		Arg[] args = field.getArgs(actName);
		Object[] argMessages = new String[args.length];
		for(int i = 0; i < args.length; i++ ){
			Arg arg = args[i];
			if(arg.isResource()){
				argMessages[i] = apps.getString(arg.getKey());
			}
			else{
				argMessages[i] = arg.getKey();
			}
		}

		return MessageFormat.format(message, argMessages);
	}
}
