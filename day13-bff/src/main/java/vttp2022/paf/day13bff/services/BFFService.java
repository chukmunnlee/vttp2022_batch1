package vttp2022.paf.day13bff.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.paf.day13bff.models.BFF;
import vttp2022.paf.day13bff.repositories.BFFRepository;

@Service
public class BFFService {

    @Autowired
    private BFFRepository bffRepo;

    public void addNewBff(BFF bff) throws BFFException {

        Optional<BFF> opt = bffRepo.findBffByEmail(bff.getEmail());
        if (opt.isPresent())
            throw new BFFException("%s is already your BFF".formatted(bff.getName()));

        if (!bffRepo.insertBff(bff))
            throw new BFFException("Cannot add %s as BFF. Please check with admin".formatted(bff.getEmail()));
    }

    public List<BFF> getAllBffs() {
        return bffRepo.selectAllBffs();
    }
    
}
