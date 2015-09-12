

import java.lang.reflect.Proxy;

public class MatchService {
	public MatchService() {

		PersonBean joe = getPersonInfo("joe", "male", "running");

		PersonBean ownerProxy = getOwnerProxy(joe);
		
		System.out.println("Name is " + ownerProxy.getName());
		System.out.println("Interests is " + ownerProxy.getInterests());

		ownerProxy.setInterests("Bowling");
		System.out.println("Interests are " + ownerProxy.getInterests());
		ownerProxy.setHotOrNotRating(50);
		System.out.println("Rating is " + ownerProxy.getHotOrNotRating());
		ownerProxy.setHotOrNotRating(40);
		System.out.println("Rating is " + ownerProxy.getHotOrNotRating());

		System.out.println("**************");

		PersonBean nonOwnerProxy = getNonOwnerProxy(joe);
		System.out.println("Name is " + nonOwnerProxy.getName());
		System.out.println("Interests are " + nonOwnerProxy.getInterests());
		nonOwnerProxy.setInterests("haha");
		System.out.println("Interests are " + nonOwnerProxy.getInterests());
		nonOwnerProxy.setHotOrNotRating(60);
		System.out.println("Rating is " + nonOwnerProxy.getHotOrNotRating());

	}

	PersonBean getPersonInfo(String name, String gender, String interests) {
		PersonBean person = new PersonBeanImpl();
		person.setName(name);
		person.setGender(gender);
		person.setInterests(interests);
		return person;
	}

	PersonBean getOwnerProxy(PersonBean person) {
		return (PersonBean) Proxy.newProxyInstance(person.getClass()
				.getClassLoader(), person.getClass().getInterfaces(),
				new OwnerInvocationHandler(person));
	}

	PersonBean getNonOwnerProxy(PersonBean person) {
		return (PersonBean) Proxy.newProxyInstance(person.getClass()
				.getClassLoader(), person.getClass().getInterfaces(),
				new NonOwnerInvocationHandler(person));
	}
}
