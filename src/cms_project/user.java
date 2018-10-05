
package cms_project;


public class user {
    private int s_id;
    private String s_name;
    private String faname;
    private String m_name;
    private String s_password;
    private int s_contact;
    private int s_age;
    private String address;
 
     private String gender;
      private String blood;
    
    public user(int Id,String name,String Faname,String Mname,String password,int contact,int age, String Address,String Gender,String Blood)
    {
        this.s_id=Id;
        this.s_name=name;
        this.s_password=password;
        this.s_contact=contact;
        this.s_age=age;
        this.address = Address;
        this.faname = Faname;
        this.m_name = Mname;
        this.gender = Gender;
        this.blood = Blood;
        
        
    }

    user(int aInt, String string, String string0, String string1, String string2, int aInt0, int aInt1, String string3, String string4) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public int getId()
    {
        return s_id;
    }
     public String getname() 
    {
        return s_name;
    }
     public String getFname() 
    {
        return faname;
    }
     public String getmname() 
    {
        return m_name;
    }
      public String  getpassword()
    {
        return s_password;
    }
       public int getcontact()
    {
        return s_contact;
    }
          public int getage()
    {
        return s_age;
    }

   public String getaddress() 
    {
        return address;
    }
public String getgender() 
    {
        return gender;
    }
public String getblood() 
    {
        return blood;
    }
  

   
}
