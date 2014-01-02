package sn.techabiz.izipay.web.commons;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;

public class VMOutils {

	public static <T> Boolean valider(T T) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<T>> constraintViolations = validator
				.validate(T);
		if (constraintViolations.size() > 0) {

			String msg = "";
			for (ConstraintViolation<T> cv : constraintViolations) {
				msg = cv.getRootBeanClass().getSimpleName() + "."
						+ cv.getPropertyPath() + " " + cv.getMessage();

				Messagebox.show(msg);
			}
			
			return false;

		}else{
			return true;
		}

	}

	

	public static void rafraichir(String msg) {

		Messagebox.show(msg, "Information", Messagebox.OK, Messagebox.INFORMATION, new EventListener<Event>() {
			
			@Override
			public void onEvent(Event event) throws Exception {
				Integer i = (Integer) event.getData();
				if(i == Messagebox.OK){
					Executions.sendRedirect("/test.zul");
				}
			}
		});
	}
}
