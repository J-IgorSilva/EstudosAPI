using Microsoft.EntityFrameworkCore;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

builder.Services.AddDbContext<AppDbContext>(option => option.UseInMemoryDatabase("TarefasDb"));

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}
app.MapGet("/", () => "ola mundo");

app.MapGet("frases", async () => await new HttpClient().GetAsync("HTTPS://JSONPLACEHOLDER.TYPICODE.COM/"));

app.MapGet("/Tarefas", async (AppDbContext db) => await db.Tarefas.ToListAsync());

app.MapPost("/Tarefas",async(Tarefa tarefa, AppDbContext db) => 
{
    db.Tarefas.Add(tarefa);
    await db.SaveChangesAsync();
    return Results.Created($"/tarefas/{tarefa.Id}", tarefa);
});

app.MapPut("/Tarefas/{id}", async (int id, Tarefa inputeTarefa, AppDbContext db) =>
{
    var tarefa = await db.Tarefas.FindAsync(id);
    if (tarefa is null) return Results.NotFound();

    tarefa.Name = inputeTarefa.Name;
    tarefa.IsConcluida = inputeTarefa.IsConcluida;

    await db.SaveChangesAsync();
    return Results.NoContent();

});

app.MapDelete("/Tarefas/{id}", async (int id, AppDbContext db) =>
{
    if (await db.Tarefas.FindAsync(id) is Tarefa tarefa)
    {
        db.Tarefas.Remove(tarefa);
        await db.SaveChangesAsync();

    }
    return Results.NotFound();
});


app.Run();

class Tarefa
{
    public string? Name { get; set; }

    public int Id { get; set; }

    public bool IsConcluida { get; set; }
}
class AppDbContext : DbContext
{
    public AppDbContext(DbContextOptions<AppDbContext> options): base(options)
    {

    }
    public DbSet<Tarefa>Tarefas=>Set<Tarefa>();
}