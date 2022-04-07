package vttp2022.paf.day13bff.repositories;

public interface Queries {

    public static final String SQL_SELECT_BFF_BY_EMAIL = "select * from bff where email = ?";

    public static final String SQL_INSERT_BFF = 
        "insert into bff (email, name, phone, status, pass_phrase, dob) values (?, ?, ?, ?, sha1(?), ?)";

    public static final String SQL_SELECT_ALL_BFF = 
        "select email, name, phone, status, dob from bff order by name";

    public static final String SQL_DELETE_BFF_BY_EMAIL =
        "delete from bff where email = ?";
    
}
