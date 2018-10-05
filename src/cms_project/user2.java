
package cms_project;

/**
 *
 * @author MINHAZUL ISLAM MIN
 */
public class user2 {
    
    private int t_id;
    private String t_name;
    private String t_password;
    private String t_email;
    private int t_age;
    private int t_phone;
    private String t_room;
    private String t_gender;
   
    
     
    public user2(int ID,String Name,String password,String email,int age,int Phone,String Room,String gend)
    {
        this.t_id=ID;
        this.t_name=Name;
        this.t_password=password;
        this.t_email=email;
        this.t_age=age;
        this.t_phone=Phone;
        this.t_room=Room;
        this.t_gender=gend;
        
    }

    
    public int getID()
    {
        return t_id;
    }
     public String getName() 
    {
        return t_name;
    }
      public String  getpassword()
    {
        return t_password;
    }
       public String getemail()
    {
        return t_email;
    }
          public int getage()
    {
        return t_age;
    }
       public int getpn()
    {
        return t_phone;
    }
       public String getroom()
    {
        return t_room;
    }
       public String getgenderrr()
    {
        return t_gender;
    }

    Object getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Object getname() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Object getFname() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Object getmname() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Object getcontact() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Object getaddress() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Object getgender() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Object getblood() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}


