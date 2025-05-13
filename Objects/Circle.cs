using System;
using System.Collections.Generic;
using System.Drawing.Drawing2D;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab5.Objects
{
    internal class Circle:BaseObject
    {
         public int timer = 50;
        public Action<Player> OnPlayerOverlap;
        public Circle(float x, float y, float angle) : base(x, y, angle)
        { 

        
        }
        public override void Render(Graphics g)
        {
            g.FillEllipse(new SolidBrush(Color.Green), -12, -12, 40, 40);
            var font = new Font("Arial", 10, FontStyle.Bold);
            var textSize = g.MeasureString(timer.ToString(), font);
            g.DrawString(timer.ToString(), font, Brushes.Black,
                        -textSize.Width / 2, -textSize.Height / 2);
        }

        public void UpdateTime(int newTime)
        {
            timer = newTime;
        }
    
        public override GraphicsPath GetGraphicsPath()
        {
           var path=base.GetGraphicsPath();
            path.AddEllipse(-12, -12, 20, 20);
            return path;
        }

        public override void Overlap(BaseObject obj)
        {
            base.Overlap(obj);
            if (obj is Player)
            {
                OnPlayerOverlap(obj as Player);
            }
            if (obj is Player player)
            {
                OnPlayerOverlap?.Invoke(player); // Безопасный вызов
            }
        }

    }
}
