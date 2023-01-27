using Dapper.Contrib.Extensions;
using System.Runtime.CompilerServices;
using TarefasApi.Data;
using static TarefasApi.Data.TarefaContext;

namespace TarefasApi.EndPoints
{
    public static class TarefasEndPoints
    {
        public static void MapTarefasEndPoins(this WebApplication app)
        {
            app.MapGet("/",()=>$"Bem vindo a API tarefas {DateTime.Now}");

            app.MapGet("/tarefas", async (Getconnection connetionGetter) =>
            {
                using var con = await connetionGetter();
                var tarefas = con.GetAll<Tarefa>().ToList();
                if (tarefas is null)
                    return Results.NotFound();
                return Results.Ok(tarefas);
            });

            app.MapGet("/tarefas/{id}", async (Getconnection connetionGetter, int id) =>
            {
                using var con = await connetionGetter();
                var tarefa = con.Get<Tarefa>(id);
                if (tarefa is null)
                    return Results.NotFound();
                return Results.Ok(tarefa);
            });

            app.MapPost("/tarefas", async (Getconnection connetionGetter, Tarefa tarefa) =>
            {
                using var con = await connetionGetter();
                var id = con.Insert(tarefa);
                return Results.Created($"/tarefas/{id}", tarefa);
            });
        }
    }
    
}
