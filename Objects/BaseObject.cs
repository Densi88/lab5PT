using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing.Drawing2D;
using System.Reflection.Metadata;

namespace lab5.Objects
{
     class BaseObject
    {
        public bool IsActive { get; set; } = true;
        public float X;
        public float Y;
        public float angle;
        public Action<BaseObject, BaseObject> OnOverlap;

        public BaseObject(float x, float y, float currentAngle)
        {
            X = x;
            Y = y;
            angle = currentAngle;
        }
        public Matrix GetTransform()
        {
            var matrix = new Matrix();
            matrix.Translate(X, Y); // Сначала смещаем в нужную позицию
            matrix.Rotate(angle);
            return matrix;
        }

        public virtual void Render(Graphics g)
        {

        }

        public virtual GraphicsPath GetGraphicsPath()
        {
            
            return new GraphicsPath();
        }
        public virtual bool Overlaps(BaseObject obj, Graphics g)
        {
            var path1 = this.GetGraphicsPath();
            var path2 = obj.GetGraphicsPath();

            // применяем к объектам матрицы трансформации
            path1.Transform(this.GetTransform());
            path2.Transform(obj.GetTransform());

            // используем класс Region, который позволяет определить 
            // пересечение объектов в данном графическом контексте
            var region = new Region(path1);
            region.Intersect(path2); // пересекаем формы
            return !region.IsEmpty(g); // если полученная форма не пуста то значит было пересечение
        }
        public virtual void Overlap(BaseObject obj)
        {
            if(this.OnOverlap!= null)
            {
                this.OnOverlap(this, obj);
            }
        }
    
    }
}
