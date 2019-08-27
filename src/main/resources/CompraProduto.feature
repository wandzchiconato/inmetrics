Feature: CompraProduto

  Scenario: Comprar produto com um clique
    Given usuario acessa o site de ecommerce "https://demo.cs-cart.com"
    Then Clica no menu e seleciona Electronics e depois Laptops
    Then Usuario clica no modelo desejado
    And clica em Buy Now With 1-Click
    Then usuario preenche os dados "Your Name" e  "Your Phone"
    Then clica em Submit
