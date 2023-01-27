using TarefasApi.EndPoints;
using TarefasApi.Extensions;

var builder = WebApplication.CreateBuilder(args);
builder.AddPersistence();

var app = builder.Build();

app.MapTarefasEndPoins();

app.Run();

app.MapGet("/", () => "Hello World!");

