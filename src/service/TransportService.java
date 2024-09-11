package service;

import domain.Transport;
import repository.TransportRepository;

import java.util.Optional;

public class TransportService {
    public Optional<Transport> saveTransport(Transport transport){
        return new TransportRepository().save(transport);
    }
}
