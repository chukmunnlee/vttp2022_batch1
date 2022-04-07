package vttp2022.paf.day13bff.models;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.util.MultiValueMap;

public class ConversionUtils {

    public static BFF convert(MultiValueMap<String, String> form) {
        BFF bff = new BFF();
        bff.setEmail(form.getFirst("email"));
        bff.setName(form.getFirst("name"));
        bff.setPhone(form.getFirst("phone"));
        bff.setStatus(form.getFirst("status"));
        bff.setPassphrase(form.getFirst("passphrase"));

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dob = format.parse(form.getFirst("dob"));
            bff.setDob(dob);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bff;
    }

    public static BFF convert(SqlRowSet rs) {
        BFF bff = new BFF();
        bff.setEmail(rs.getString("email"));
        bff.setName(rs.getString("name"));
        bff.setPhone(rs.getString("phone"));
        bff.setStatus(rs.getString("status"));
        bff.setDob(rs.getDate("dob"));
        return bff;
    }
    
}
