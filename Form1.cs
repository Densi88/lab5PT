using lab5.Objects;

namespace lab5
{
    public partial class Form1 : Form
    {
        List<BaseObject> objects = new();
        Marker marker;
        Player player;
        Circle circle;
        Random random=new Random();
        int score = 0;
        public Form1()
        {
            InitializeComponent();
            player = new Player(mainPb.Width / 2, mainPb.Height / 2, 0);
            objects.Add(player);
            marker = new Marker(mainPb.Width / 2+50, mainPb.Height / 2+20, 0);
            objects.Add(marker);
            circle = new Circle(mainPb.Width / 2 + 20, mainPb.Height / 2 + 10, 0);
            objects.Add(circle);
            player.OnOverlap += (p, obj) => {
                txtLog.Text = $"[{DateTime.Now:HH:mm:ss:ff}] Игрок пересекся с {obj}\n" + txtLog.Text;
            };

            // добавил реакцию на пересечение с маркером
            player.OnMarkerOverlap += (m) =>
            {
                objects.Remove(m);
                marker = null;
            };

            circle.OnPlayerOverlap += (p) => {
                txtLog.Text= $"[{DateTime.Now:HH:mm:ss:ff}] Игрок пересекся с шаром\n" + txtLog.Text;
                objects.Remove(circle);
                circle = null;
                scoreLabel.Text = $"Очки: {score}";

            };
        }

        private void mainPb_paint(object sender, PaintEventArgs e)
        {
            var g = e.Graphics;
            var matrix = g.Transform;
            foreach (var obj in objects.ToList())
            {
                if (obj != player && player.Overlaps(obj, g))
                {
                    player.Overlap(obj);
                    obj.Overlap(player);
                }
            }

            // рендерим объекты
            foreach (var obj in objects)
            {
                g.Transform = obj.GetTransform();
                obj.Render(g);
            }
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
            if (marker!=null)
            {
                float dx = marker.X - player.X;
                float dy = marker.Y - player.Y;

                float length = MathF.Sqrt(dx * dx + dy * dy);
                dx /= length;
                dy /= length;

                player.X += dx * 12;
                player.Y += dy * 12;
               

            }
            if (circle == null)
            {
                var circleHeight = 40;
                var circleLength = 40;
                var randomX=random.Next(0, mainPb.Width - circleLength);
                var randomY= random.Next(0, mainPb.Height-circleHeight);
                circle = new Circle(randomX, randomY, 0);
                objects.Add(circle);

                circle.OnPlayerOverlap += (p) =>
                {
                    if (circle != null)
                    {
                        txtLog.Text = $"[{DateTime.Now:HH:mm:ss:ff}] Игрок пересекся с шаром\n" + txtLog.Text;
                        objects.Remove(circle);
                        circle = null;
                        score++;
                        scoreLabel.Text = $"Очки: {score}";
                    }
                };
            }
           
                circle.timer = circle.timer - 1;
                circle.UpdateTime(circle.timer);
            if (circle.timer==0)
            {
                objects.Remove(circle);
                circle = null;
            }
            
            mainPb.Invalidate();

        }

        private void mainPb_MouseClick(object sender, MouseEventArgs e)
        {
            if (marker == null)
            {
                marker = new Marker(0, 0, 0);
                objects.Add(marker); // и главное не забыть пололжить в objects
            }
            marker.X = e.X;
            marker.Y=e.Y;
        }
    }
}