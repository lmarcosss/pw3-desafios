package com.example.liquigas.entity

class Gas(val size: Int, val image: String) {
    companion object {
        fun listGas(): ArrayList<Gas> {
            var gas = arrayListOf<Gas>()

            var image = "https://a-static.mlcdn.com.br/618x463/gas-de-cozinha-13kg-somente-gas-com-entrega-sem-vasilhame-ultragaz/ultragazulianopolis/d393bcb6d38711eb9dcb4201ac18500e/3681757c65fd0d6fb382ab84e35d084a.jpg"

            gas.add(Gas(13, image))
            gas.add(Gas(20, image))
            gas.add(Gas(45, image))

            return gas
        }
    }
}
