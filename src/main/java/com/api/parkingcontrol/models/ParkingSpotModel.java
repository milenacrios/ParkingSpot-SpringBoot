package com.api.parkingcontrol.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity //transforma em uma entidade no banco de dados
@Table(name = "TB_PARKING_SPOT") //nomeia a tabela para o banco
@Getter
@Setter
public class ParkingSpotModel implements Serializable {
    private static final long serialVersionUID = 1L;

    //atributor da entidade
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //gerado automaticamente
    private UUID id; //uuid = identificadores únicos
    @Column(nullable = false, unique = true, length = 10) //número da vaga do estacionamento
    private String parkingSpotNumber;
    //todo: fazer uma entidade apenas para carro e fazer o relacionamento adequado
    @Column(nullable = false, unique = true, length = 7) //placa do carro pertencente a vaga
    private String licensePlateCar;
    @Column(nullable = false, length = 70) //marca do carro
    private String brandCar;
    @Column(nullable = false, length = 70) //modelo do carro
    private String modelCar;
    @Column(nullable = false, length = 70) //cor do carro
    private String colorCar;
    @Column(nullable = false) //data de registro da vaga
    private LocalDateTime registrationDate;
    @Column(nullable = false, length = 130) //responsável pela vaga
    private String responsibleName;
    @Column(nullable = false, length = 30) //número do apartamento
    private String apartment;
    @Column(length = 30)
    @Nullable
    private String block;

}
