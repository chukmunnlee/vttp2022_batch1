package vttp2022.ssf.revision1.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import vttp2022.ssf.revision1.models.Registrations;

@Service
public class RegistrationService {

    private Map<String, Registrations> regs = new HashMap<>();

    public RegistrationService() {

        // Insert some fake data
        Registrations r = new Registrations();
        r.setEmail("wilma@gmail.com");
        r.setName("wilma");
        r.setPhone("123435");
        newRegistration(r);

        r = new Registrations();
        r.setEmail("barney@gmail.com");
        r.setName("barney");
        r.setPhone("345678");
        newRegistration(r);
    }

    public void newRegistration(final Registrations reg) {
        regs.put(reg.getName(), reg);
    }

    public Collection<Registrations> getAllRegistrations() {
        return regs.values();
    }
    public Collection<String> getAllRegisteredNames() {
        return regs.keySet();
    }
    public Registrations getUserByName(String name) {
        // Issue here!!!!
        return regs.get(name);
    }
    
}
