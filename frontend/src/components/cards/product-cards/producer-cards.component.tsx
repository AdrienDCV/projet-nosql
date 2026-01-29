
const partners = [
    {
        name: "Alexandre",
        photo: "https://www.serresvaldeloire.com/actus-conseils/wp-content/uploads/2021/10/maraicher.jpg",
        description:
            "Alexandre est producteur local et privilégie une agriculture raisonnée. Il met beaucoup d’attention dans la culture de ses légumes afin de garantir des produits sains. On retrouve souvent sa production sur les marchés locaux.",
    },
    {
        name: "Jerome",
        photo: "https://www.viametiers.fr/wp-content/uploads/2023/10/metier-maraicher.jpg",
        description:
            "Jerome est un producteur de fruits et légumes locaux qui travaille avec passion. Il cultive des produits de saison en respectant la nature et la qualité des sols. Son objectif est de proposer des fruits et légumes frais et savoureux aux habitants de la région.",
    },
    {
        name: "Cecile",
        photo: "https://www.droitconstitutionnel.org/wp-content/uploads/2023/03/jeune-femme-souriante-tenant-une-cagette-de-legumes-frais-elle-a-mis-un-petit-chapeau-de-paille-tres-tendance.jpg",
        description:
            "Cecile est une productrice engagée qui met en avant le savoir-faire local. Elle cultive une grande variété de légumes et quelques fruits, en limitant l’usage de produits chimiques. Son objectif est d’offrir des aliments sains tout en préservant l’environnement.",
    },
];

export function ProducerCard() {
    return (
        <div className="flex flex-col bg-[#FFF6E8]">
            {/* Section partenaires */}
            <section className="w-full flex justify-center py-20">
                <div className="flex flex-col md:flex-row gap-8 max-w-6xl">
                    {partners.map((partner, index) => (
                        <div
                            key={index}
                            className="flex flex-col items-center text-center rounded-2xl p-6 w-80"
                        >
                            <img
                                src={partner.photo}
                                alt={partner.name}
                                className="w-44 h-44 rounded-full object-cover mb-4 shadow-lg"
                            />

                            <h3 className="text-xl text-secondary font-semibold mb-2">
                                {partner.name}
                            </h3>

                            <p className="text-sm text-gray-700 flex-1">
                                {partner.description}
                            </p>
                        </div>
                    ))}
                </div>
            </section>
        </div>
    );
}

