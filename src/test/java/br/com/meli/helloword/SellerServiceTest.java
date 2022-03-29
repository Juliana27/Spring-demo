package br.com.meli.helloword;

import br.com.meli.helloword.entity.Seller;
import br.com.meli.helloword.repository.SellerRepository;
import br.com.meli.helloword.service.SellerService;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

//@Profile("critico")
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration
public class SellerServiceTest {

    private SellerService service;

    private SellerRepository sellerRepository; //é um objeto mockado

//    @BeforeAll
//    public static void prepaBancoDeDados() {
//        System.out.println("preparando banco de dados");
//    }

//    @BeforeEach
//    public void resetaAmbiente(){
//        System.out.println("before each");
//    }

//    @AfterAll
//    public static void resetandBancoDeDados() {
//        System.out.println("finalizando tudo");;
//    }

    @BeforeEach
    public void before() {
        this.sellerRepository = Mockito.mock(SellerRepository.class);
        //this.sellerRepository = new SellerRepository();
        this.service = new SellerService(sellerRepository);
    }

//    @AfterEach
//    public void after(){
//        this.service = null;
//        System.out.println("after each");
//    }

    @Order(0)
    @Test
    public void primeiroTest(){
        System.out.println("primeiro teste");
        assertTrue(true);
    }

//    @Order(1)
//    @Test
//    public void segundoTest(){
//        SellerService service = new SellerService();
//        Seller seller = Seller.builder().codigo("MLB987").nome("Juliana").dataNascimento(LocalDate.parse("1992-12-27")).build();
//        Seller result = service.salva(seller);
//        assertNotNull(result);
//    }

    @Test
    public void deveSalvarUmSeller() {

        // setup
        Seller seller = Seller.builder().codigo("MLB987").nome("Kenyo").dataNascimento(LocalDate.parse("1980-02-25"))
                .build();
        Mockito.when(this.sellerRepository.insere(any())).thenReturn(seller);

        // executando a unidade under test
        Seller result = this.service.salva(seller);

        // check
        assertNotNull(result);
    }

    @Test
    public void naoDeveSalvarUmSellerQuandoMenorIdade() {
        Seller seller = Seller.builder().codigo("MLB987").nome("Juliana").dataNascimento(LocalDate.parse("2007-12-27"))
                .build();
        RuntimeException result = assertThrows(RuntimeException.class, ()->this.service.salva(seller));
        assertEquals("um vendedor deve ser maior de idade",result.getMessage());
    }

    @Test
    public void deveExcluirUmSeller() {
        boolean result = service.remove("MLB123");
        assertTrue(result);
    }

    @DisplayName(value = "nome que_=   eu_quero")
    @Test
    public void naoDeveExcluirUmSeller() {
//        Throwable exception = assertThrows(RuntimeException.class, () -> {
//            boolean result = service.remove("");
//        });
        Throwable exception = assertThrows(RuntimeException.class,()->service.remove(""));
        assertEquals("informe o codigo", exception.getMessage() );
    }
}
