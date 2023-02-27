package com.skka.adapter.outbound.jpa.customer;

import static com.skka.adapter.outbound.jpa.customer.CustomerEntity.toCustomerEntity;
import static java.util.Optional.empty;

import com.skka.domain.customer.Customer;
import com.skka.domain.customer.repository.CustomerRepository;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@Getter
@RequiredArgsConstructor
public class CustomerRepositoryAdapter implements CustomerRepository {

    private final CustomerJpaRepository jpaRepository;

    @Override
    public Customer save(Customer customer) {
        CustomerEntity entity = toCustomerEntity(customer);
        jpaRepository.save(entity);
        return entity.toCustomerReturn();
    }

    @Override
    public Optional<Customer> findById(long id) {
        CustomerEntity foundEntity = jpaRepository.findById(id)
            .orElseThrow(() -> null); // new IllegalArgumentException("고객을 찾지 못했습니다.")
        return Optional.ofNullable(foundEntity.toCustomerReturn());
    }
}
