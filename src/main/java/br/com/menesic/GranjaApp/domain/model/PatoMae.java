package br.com.menesic.GranjaApp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatoMae {
        private UUID id;
        private Pato pato;
        private Pato mae;
}
