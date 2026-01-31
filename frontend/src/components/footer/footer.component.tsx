import InstagramIcon from '@mui/icons-material/Instagram';

export function FooterComponent() {
    return (
        <footer className="w-full bg-[#FFD086] text-secondary py-6">
            <div className="max-w-6xl mx-auto flex flex-col md:flex-row items-center justify-between px-6 text-sm">
                {/* Texte copyright */}
                <p className="mb-2 md:mb-0">© 2026 — QUETTA</p>

                {/* Noms ou sections */}
                <div className="flex flex-row items-center gap-6">
                    <span>Contact</span>
                    <span>Politique de confidentialité</span>
                    <span>Nos conditions generales</span>
                    <InstagramIcon className="text-secondary" style={{fontSize: "32px" }} />
                </div>
            </div>
        </footer>
    );
}
