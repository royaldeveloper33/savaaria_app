
        package com.example.app

        import android.content.Intent
                import android.os.Bundle
                import android.widget.ImageView
                import androidx.appcompat.app.AppCompatActivity

        class vehicals : AppCompatActivity() {

            lateinit var autoImg: ImageView
            lateinit var bikeImg: ImageView
            lateinit var lorryImg: ImageView

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_vehicals)

                autoImg = findViewById(R.id.autoImg)
                bikeImg = findViewById(R.id.bikeImg)
                lorryImg = findViewById(R.id.lorryImg)

                autoImg.setOnClickListener {
                    openInfo()
                }

                bikeImg.setOnClickListener {
                    openInfo()
                }

                lorryImg.setOnClickListener {
                    openInfo()
                }
            }

            private fun openInfo() {
                val intent = Intent(this, info::class.java)
                startActivity(intent)
            }
        }

