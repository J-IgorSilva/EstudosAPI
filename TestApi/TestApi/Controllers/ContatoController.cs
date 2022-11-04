using Microsoft.AspNetCore.Mvc;
using TestApi.Context;
using TestApi.Entities;

namespace TestApi.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class ContatoController : ControllerBase
    {
        private readonly AgendaContext _context;

        public ContatoController(AgendaContext context)
        {
            _context = context;
        }
        [HttpPost]
        public IActionResult Create(Contato contato)
        {
            _context.Add(contato);
            _context.SaveChanges();
            return CreatedAtAction(nameof(ObterPorId), new { id = contato.Id }, contato);
        }
        [HttpGet("{id}")]
        public IActionResult ObterPorId(int id)
        {
            var contato = _context.Contatos.Find(id);

            if (contato == null)
            {
                return NotFound();
            }
            return Ok(contato);
        }
        [HttpGet("BuscarPorNome")]
        public IActionResult BuscarPorNome(string nome)
        {
            var contatos = _context.Contatos.Where(x => x.Nome.Contains(nome));
            return Ok(contatos);
        }

        [HttpPut("{id}")]
        public IActionResult Atualizar( int id, Contato contato)
        {
            var contatoBanco = _context.Contatos.Find(id);
            if (contatoBanco == null)
            
                return NotFound();

            contatoBanco.Nome = contato.Nome;
            contatoBanco.Telefone = contato.Telefone;
            contatoBanco.Ativo = contato.Ativo;

            _context.Contatos.Update(contatoBanco);
            _context.SaveChanges();

            Console.WriteLine("Contato Atualizado Com Sucesso");
            return Ok(contatoBanco);
        }

        [HttpDelete("{id}")]
        public IActionResult Delete(int id)
        {
            var contatoBanco = _context.Contatos.Find(id);
            if (contatoBanco == null)

                return NotFound();
            _context.Contatos.Remove(contatoBanco);
            _context.SaveChanges();
            return NoContent();
        }
    }
}
