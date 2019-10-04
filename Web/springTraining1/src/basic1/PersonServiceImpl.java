package basic1;

public class PersonServiceImpl implements PersonService {
	private String name;
	private int age;

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void sayHello() {
		System.out.println("?Ì¸§: " + name);
		System.out.println("³ª?Ì: " + age);
	}
}
//public class PersonServiceImpl implements PersonService{
//
//    private String name;
//    private int age;
//
//    public String getName() {
//        return this.name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getAge() {
//        return this.age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//	@Override
//	public void sayHello() {
//		// TODO Auto-generated method stub
//		
//	}
//	
//}
