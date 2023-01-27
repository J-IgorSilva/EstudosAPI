using System.Data.SqlClient;
using static TarefasApi.Data.TarefaContext;

namespace TarefasApi.Extensions
{
    public static class ServiceColectionExtensions
    {
        public static WebApplicationBuilder AddPersistence(this WebApplicationBuilder builder) 
        {
            var ConnectionStrings = builder.Configuration.GetConnectionString("ConexaoPadrao");
            builder.Services.AddScoped<Getconnection>(sp => async () =>
            {
                var connetion = new SqlConnection(ConnectionStrings);
                await connetion.OpenAsync();
                return connetion;
            });
            return builder;
        }
    }
}
