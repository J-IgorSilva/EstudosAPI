using Refit;
using System;
using System.Threading.Tasks;

namespace CepApi
{
    class Program
    {
        static async Task Main(string[] args)
        {
            try
            {
                var cepCliente = RestService.For<ICepApiService>("http://viacep.com.br/");

                Console.WriteLine("Informe Seu CEP");

                var cepInformado = Console.ReadLine().ToString();

                Console.WriteLine("Consultando CEP informado" + cepInformado);

                var address = await cepCliente.GetAddressAsync(cepInformado);

                Console.Write($"\nLogradouro:{address.Logradouro},\nBairro{address.Bairro},\nEstado{address.Uf}");

                Console.ReadKey();
            }
            catch (Exception e)
            {

                Console.WriteLine("Cep não Encontrado" + e.Message);
            }
        }
    }
}
