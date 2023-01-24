using System.Collections.ObjectModel;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace APICatalogo.Models;
[Table("Categorias")]
public class Categoria
{
    public Categoria()
    {
        Produtos= new Collection<Produto>();      
    }
    [Key]
    public int Id { get; set; }

    [Required]
    [StringLength(80)]
    public string? Nome { get; set; }

    [Required]
    [StringLength(400)]
    public string? ImgUrl { get; set; }
    public ICollection<Produto>? Produtos { get; set; }
}
